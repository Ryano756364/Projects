package com.ryanscompany;

import com.ryanscompany.dao.LoanDao;
import com.ryanscompany.model.Customer;
import com.ryanscompany.model.Loan;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;

public class LoanLogic {

    private LoanDao loanDao;
    private Loan loan;

    //Writer to loan table in database
    public void checkForConventional(Customer newCustomer) {
        if (conventionalCreditCheck(newCustomer.getCreditScore()) &&
                conventionalIncomeCheck(newCustomer.getIncomeYearly(), newCustomer.getMonthlyDebtPayments())) {

            Loan loan = new Loan(newCustomer.getCustomer_id(),
                    "Conventional",
                    30,
                    300000,
                    calculateDTI(newCustomer.getIncomeYearly(), newCustomer.getMonthlyDebtPayments()),
                    LocalDate.parse(newCustomer.getApplicationDate()).plusDays(90));

            loanDao.createLoan(loan);

            System.out.println("Customer qualifies conventional.");
        }
    }

    public void checkForFHA(Customer newCustomer) {
        if (FHACreditCheck(newCustomer.getCreditScore()) &&
                FHAIncomeCheck(newCustomer.getIncomeYearly(), newCustomer.getMonthlyDebtPayments())) {

            Loan loan = new Loan(newCustomer.getCustomer_id(),
                    "FHA",
                    30,
                    300000,
                    calculateDTI(newCustomer.getIncomeYearly(), newCustomer.getMonthlyDebtPayments()),
                    LocalDate.parse(newCustomer.getApplicationDate()).plusDays(90));

            loanDao.createLoan(loan);

            System.out.println("Customer qualifies FHA.");
        }
    }

    //Loan logic
    private boolean conventionalCreditCheck(BigDecimal creditScore) {
        return creditScore.compareTo(BigDecimal.valueOf(720)) >= 0;
    }

    private boolean conventionalIncomeCheck(BigDecimal incomeYearly, BigDecimal monthlyDebtPayments) {
        BigDecimal monthlyIncome = incomeYearly.divide(BigDecimal.valueOf(12));
        BigDecimal debtToIncomeRatio = monthlyDebtPayments.divide(monthlyIncome);

        return debtToIncomeRatio.compareTo(BigDecimal.valueOf(50)) <= 0;
    }

    private boolean FHACreditCheck(BigDecimal creditScore) {
        return creditScore.compareTo(BigDecimal.valueOf(680)) >= 0;
    }

    private boolean FHAIncomeCheck(BigDecimal incomeYearly, BigDecimal monthlyDebtPayments) {
        BigDecimal monthlyIncome = incomeYearly.divide(BigDecimal.valueOf(12));
        BigDecimal debtToIncomeRatio = monthlyDebtPayments.divide(monthlyIncome);

        return debtToIncomeRatio.compareTo(BigDecimal.valueOf(57)) <= 0;
    }


    //Calculators
    private BigDecimal calculateDTI(BigDecimal incomeYearly, BigDecimal monthlyDebtPayments) {
        BigDecimal monthlyIncome = incomeYearly.divide(BigDecimal.valueOf(12));
        return monthlyDebtPayments.divide(monthlyIncome);
    }
}
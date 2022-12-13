package com.ryanscompany.dao;

import com.ryanscompany.model.Customer;
import com.ryanscompany.model.Loan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcLoanDao implements LoanDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcLoanDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final double currentInterestRate = 0.065;
    private final BigDecimal THIRTY_YEARS = BigDecimal.valueOf(30);
    private final BigDecimal LOAN_CALC_NUMERATOR = BigDecimal.valueOf(Math.pow(1 + currentInterestRate, THIRTY_YEARS.doubleValue()))
            .subtract(BigDecimal.valueOf(1));
    private final BigDecimal LOAN_CALC_DENOMINATOR = BigDecimal.valueOf(currentInterestRate)
            .multiply(BigDecimal.valueOf(Math.pow(1 + currentInterestRate, THIRTY_YEARS.doubleValue())));
    private final int ROUNDING_MODE = 2;

    @Override
    public Loan getLoanById(int loanId) {
        Loan loan = null;

        String sql = "SELECT loan_id, customer_id, program, term_years, loan_amount, dti, expiration " +
                "FROM loan " +
                "WHERE loan_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, loanId);
        if (results.next()){
            loan = mapRowToLoan(results);
        }

        return loan;
    }

    @Override
    public List<Loan> getLoans() {
        List<Loan> listOfLoans = new ArrayList<>();

        String sql = "SELECT l.loan_id, l.customer_id, l.program, l.term_years, l.loan_amount, l.dti, l.expiration " +
                "FROM loan AS l " +
                "JOIN customer AS c ON c.customer_id = l.customer_id " +
                "ORDER BY c.customer_id;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        while(sqlRowSet.next()){
            Loan loan = mapRowToLoan(sqlRowSet);
            listOfLoans.add(loan);
        }

        return listOfLoans;
    }

    @Override
    public List<Loan> getLoansByCustomerId(int customerId) {
        List<Loan> listOfLoans = new ArrayList<>();

        String sql = "SELECT l.loan_id, l.customer_id, l.program, l.term_years, l.loan_amount, l.dti, l.expiration " +
                "FROM loan AS l " +
                "JOIN customer AS c ON c.customer_id = l.customer_id " +
                "WHERE l.customer_id = ? " +
                "ORDER BY c.customer_id;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, customerId);

        while(sqlRowSet.next()){
            Loan loan = mapRowToLoan(sqlRowSet);
            listOfLoans.add(loan);
        }

        return listOfLoans;
    }

    @Override
    public Loan createLoan(Loan newLoan) {
        String sql = "INSERT INTO loan (customer_id, program, term_years, loan_amount, dti, expiration) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING loan_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newLoan.getCustomer_id(), newLoan.getProgram(),
                newLoan.getTermYears(), newLoan.getLoanAmount(), newLoan.getDti(), newLoan.getExpiration());
        return getLoanById(newId);
    }

    @Override
    public void deleteLoan(int loanId) {
        String sql = "DELETE FROM loan " +
                "WHERE loan_id = ?;";

        jdbcTemplate.update(sql, loanId);
    }

    @Override
    public void checkForConventional(Customer customer) {
        if (conventionalCreditCheck(customer.getCreditScore()) &&
                conventionalIncomeCheck(customer.getIncomeYearly(), customer.getMonthlyDebtPayments())) {

            Loan loan = new Loan(customer.getCustomer_id(),
                    "Conventional",
                    THIRTY_YEARS,
                    conventionalCalculateLoanAmount(customer.getIncomeYearly(), customer.getMonthlyDebtPayments()),
                    calculateDTI(customer.getIncomeYearly(), customer.getMonthlyDebtPayments()),
                    LocalDate.parse(customer.getApplicationDate()).plusDays(90));

            createLoan(loan);

            System.out.println("Customer qualifies conventional.");
        }
    }

    @Override
    public void checkForFHA(Customer customer) {
        if (FHACreditCheck(customer.getCreditScore()) &&
                FHAIncomeCheck(customer.getIncomeYearly(), customer.getMonthlyDebtPayments())) {

            Loan loan = new Loan(customer.getCustomer_id(),
                    "FHA",
                    THIRTY_YEARS,
                    FHACalculateLoanAmount(customer.getIncomeYearly(), customer.getMonthlyDebtPayments()),
                    calculateDTI(customer.getIncomeYearly(), customer.getMonthlyDebtPayments()),
                    LocalDate.parse(customer.getApplicationDate()).plusDays(90));


            createLoan(loan);

            System.out.println("Customer qualifies FHA.");
        }
    }


    //Mapping helper
    private Loan mapRowToLoan(SqlRowSet results){
        Loan loan = new Loan();

        loan.setLoan_id(results.getInt("loan_id"));
        loan.setCustomer_id(results.getInt("customer_id"));
        loan.setProgram(results.getString("program"));
        loan.setTermYears(results.getBigDecimal("term_years"));
        loan.setLoanAmount(results.getBigDecimal("loan_amount"));
        loan.setDti(results.getBigDecimal("dti"));
        if (results.getDate("expiration") != null) {
            loan.setExpiration(results.getDate("expiration").toLocalDate());
        }

        return loan;
    }


    //Loan logic
    //Conventional
    private boolean conventionalCreditCheck(BigDecimal creditScore) {
        return creditScore.compareTo(BigDecimal.valueOf(720)) >= 0;
    }
    private boolean conventionalIncomeCheck(BigDecimal incomeYearly, BigDecimal monthlyDebtPayments) {
        BigDecimal monthlyIncome = incomeYearly.divide(BigDecimal.valueOf(12), ROUNDING_MODE);
        BigDecimal debtToIncomeRatio = monthlyDebtPayments.divide(monthlyIncome, ROUNDING_MODE);

        return debtToIncomeRatio.compareTo(BigDecimal.valueOf(50)) <= 0;
    }
    private BigDecimal conventionalCalculateLoanAmount(BigDecimal yearlyIncome, BigDecimal monthlyDebt){
        //This calculation is for demonstration purposes only and does not take into account other liabilities
        BigDecimal dtiCap = BigDecimal.valueOf(0.50);
        BigDecimal monthlyPaymentLeftOver = (yearlyIncome.divide(BigDecimal.valueOf(12), ROUNDING_MODE)).multiply(dtiCap).subtract(monthlyDebt);
        return (monthlyPaymentLeftOver.multiply(LOAN_CALC_NUMERATOR)).divide(LOAN_CALC_DENOMINATOR, ROUNDING_MODE);
    }

    //FHA
    private boolean FHACreditCheck(BigDecimal creditScore) {
        return creditScore.compareTo(BigDecimal.valueOf(680)) >= 0;
    }
    private boolean FHAIncomeCheck(BigDecimal incomeYearly, BigDecimal monthlyDebtPayments) {
        BigDecimal monthlyIncome = incomeYearly.divide(BigDecimal.valueOf(12), ROUNDING_MODE);
        BigDecimal debtToIncomeRatio = monthlyDebtPayments.divide(monthlyIncome, ROUNDING_MODE);

        return debtToIncomeRatio.compareTo(BigDecimal.valueOf(57)) <= 0;
    }
    private BigDecimal FHACalculateLoanAmount(BigDecimal yearlyIncome, BigDecimal monthlyDebt){
        //This calculation is for demonstration purposes only and does not take into account other liabilities
        BigDecimal dtiCap = BigDecimal.valueOf(0.52);
        BigDecimal monthlyPaymentLeftOver = (yearlyIncome.divide(BigDecimal.valueOf(12), ROUNDING_MODE)).multiply(dtiCap).subtract(monthlyDebt);
        return (monthlyPaymentLeftOver.multiply(LOAN_CALC_NUMERATOR)).divide(LOAN_CALC_DENOMINATOR, ROUNDING_MODE);
    }

    //Calculators
    private BigDecimal calculateDTI(BigDecimal incomeYearly, BigDecimal monthlyDebtPayments) {
        BigDecimal monthlyIncome = incomeYearly.divide(BigDecimal.valueOf(12), ROUNDING_MODE);
        return monthlyDebtPayments.divide(monthlyIncome, ROUNDING_MODE);
    }
}
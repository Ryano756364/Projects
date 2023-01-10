package src.loans;

public interface Loan {

    // Methods required whenever a new loan is created and imported into the system

    /**
     *  Calculates clients max loan approval amount based on income, dti, interest rate,
     *  taxes, and homeowners insurance
     * @return
     */
    int findMaxLoanApprovalAmount(int yearlyIncome, int monthlyDebtPayments,
                                     double monthlyInsurancePayment, double monthlyTaxPayment);

    /**
     * Calculates clients max allowable budget for monthly P&I to then use to find max loan amount
     * @return
     */
    double findMonthlyPrincipleAndInterestPayment(int yearlyIncome, int monthlyDebtPayments,
                                                  double monthlyInsurancePayment, double monthlyTaxPayment);
}

package src.loans;

public class VA extends LoanSuper implements Loan{

    // *** FIELDS ***
    private final double MAX_DTI = 50.00;  // Technically there isn't a max for VA but for this program we will use one
    private final int MIN_LOAN_LIMIT = 100000;  // some lenders and programs may have a minimum loan limit they can work with

    private final double INTEREST_RATE = 6.14; // further software developments can include complex calculations with
    // regard to that specific client's risk and how interest rate is calcuated. For now, we will use today's average
    // on a 30-year fixed
    private final int LOAN_TERM = 360;
    private boolean isVeteran;


    // *** CONSTRUCTORS ***
    public VA (){};
    public VA(int maxLoanLimit, int minLoanLimit, double maxDTI, String creditScore, boolean isVeteran) {
        this.maxLoanSize = maxLoanLimit;
        this.minLoanSize = minLoanLimit;
        this.DTI = maxDTI;
        this.creditScore = creditScore;
        this.isVeteran = isVeteran;
    }


    // *** GETTERS AND SETTERS ***
    public int getLoanID(){
        return this.loanID;
    }
    public void setLoanID(int loanID){
        this.loanID = loanID;
    }

    public int getMaxLoanLimit() {
        return maxLoanSize;
    }
    public void setMaxLoanLimit(int maxLoanLimit) {
        this.maxLoanSize = maxLoanLimit;
    }

    public int getMinLoanLimit() {
        return minLoanSize;
    }
    public void setMinLoanLimit(int minLoanLimit) {
        if (minLoanLimit < MIN_LOAN_LIMIT){
            minLoanSize = 0;  // ensures minimum loan limit is captured
            // otherwise 0 will be stored which signifies customer is not eligible for this loan
        } else {
            minLoanSize = minLoanLimit;
        }
    }

    public double getDTI() {
        return DTI;
    }
    public void setDTI(double DTI) {
        if (DTI <= MAX_DTI){
            this.DTI = DTI;
        } else {
            this.DTI = 0.00;
        }
    }

    public String getCreditScore() {
        return creditScore;
    }
    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;  //there is no minimum credit score for VA loans
    }

    public boolean isVeteran() {
        return isVeteran;
    }
    public void setVeteran(boolean isVeteran) {
        this.isVeteran = isVeteran;
    }


    // *** METHODS ***
    @Override
    public int findMaxLoanApprovalAmount(int yearlyIncome, int monthlyDebtPayments,
                                         double monthlyInsurancePayment, double monthlyTaxPayment) {

        double monthlyPaymentBudget = findMonthlyPrincipleAndInterestPayment(yearlyIncome, monthlyDebtPayments,
                monthlyInsurancePayment, monthlyTaxPayment);
        double interestRatePercentagePerMonth = (INTEREST_RATE / 100) / 12.00;

        // no need to handle possible 0 from monthly payment budget since it's in the numerator
        int maxLoanApprovalAmount = (int)((monthlyPaymentBudget / interestRatePercentagePerMonth) *
                (1 - ( 1 / Math.pow(1 + interestRatePercentagePerMonth, LOAN_TERM))));

        return (isVeteran) ? maxLoanApprovalAmount : 0;
    }

    @Override
    public double findMonthlyPrincipleAndInterestPayment(int yearlyIncome, int monthlyDebtPayments,
                                                         double monthlyInsurancePayment, double monthlyTaxPayment){

        double monthlyPandI = yearlyIncome * (MAX_DTI / 100) / 12 - monthlyDebtPayments - monthlyInsurancePayment - monthlyTaxPayment;

        if (monthlyPandI >= 0) {
            return monthlyPandI;
        }
        return 0.00;

    }
}

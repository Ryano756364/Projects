package src.loans;

public class USDA extends LoanSuper implements Loan{

    // *** FIELDS ***
    private final double MAX_DTI = 46.00; // USDA calculations are a bit more intricate and further software
    // releases will be able to handle those calculations.
    // For this program's purpose, we will use 46% max DTI.
    private final int MIN_LOAN_LIMIT = 100000;  // some lenders and programs may have a minimum loan limit they can work with

    private final double INTEREST_RATE = 6.14; // further software developments can include complex calculations with
    // regard to that specific client's risk and how interest rate is calcuated. For now, we will use today's average
    // on a 30-year fixed
    private final int LOAN_TERM = 360;
    private final int MAX_INCOME_ALLOWED = 60000;  // USDA places cap on total household income to qualify


    // *** CONSTRUCTORS ***
    public USDA () {};
    public USDA(int maxLoanLimit, int minLoanLimit, double maxDTI, String creditScore, int maxIncomeAllowed) {
        this.maxLoanSize = maxLoanLimit;
        this.minLoanSize = minLoanLimit;
        this.DTI = maxDTI;
        this.creditScore = creditScore;
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
        int strCredit = Integer.parseInt(creditScore);
        if (strCredit > 640){
            this.creditScore = creditScore;
        } else {
            this.creditScore = null;
        }
    }


    // *** METHODS ***
    @Override
    public int findMaxLoanApprovalAmount(int yearlyIncome, int monthlyDebtPayments,
                                         double monthlyInsurancePayment, double monthlyTaxPayment) {

        int maxLoanApprovalAmount = 0;

        if (yearlyIncome < MAX_INCOME_ALLOWED) {
            double monthlyPaymentBudget = findMonthlyPrincipleAndInterestPayment(yearlyIncome, monthlyDebtPayments,
                    monthlyInsurancePayment, monthlyTaxPayment);
            double interestRatePercentagePerMonth = (INTEREST_RATE / 100) / 12.00;

            // no need to handle possible 0 from monthly payment budget since it's in the numerator
            maxLoanApprovalAmount = (int) ((monthlyPaymentBudget / interestRatePercentagePerMonth) *
                    (1 - (1 / Math.pow(1 + interestRatePercentagePerMonth, LOAN_TERM))));
        }

        return maxLoanApprovalAmount;
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

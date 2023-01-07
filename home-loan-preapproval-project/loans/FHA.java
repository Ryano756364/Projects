package loans;

public class FHA {
    private int maxLoanLimit;
    private int minLoanLimit;
    private double maxDTI;
    private int minCreditScore;

    public FHA(int maxLoanLimit, int minLoanLimit, double maxDTI, int minCreditScore) {
        this.maxLoanLimit = maxLoanLimit;
        this.minLoanLimit = minLoanLimit;
        this.maxDTI = maxDTI;
        this.minCreditScore = minCreditScore;
    }

    public int getMaxLoanLimit() {
        return maxLoanLimit;
    }

    public void setMaxLoanLimit(int maxLoanLimit) {
        this.maxLoanLimit = maxLoanLimit;
    }

    public int getMinLoanLimit() {
        return minLoanLimit;
    }

    public void setMinLoanLimit(int minLoanLimit) {
        this.minLoanLimit = minLoanLimit;
    }

    public double getMaxDTI() {
        return maxDTI;
    }

    public void setMaxDTI(double maxDTI) {
        this.maxDTI = maxDTI;
    }

    public int getMinCreditScore() {
        return minCreditScore;
    }

    public void setMinCreditScore(int minCreditScore) {
        this.minCreditScore = minCreditScore;
    }
}

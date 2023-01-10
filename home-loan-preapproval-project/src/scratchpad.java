package src;

public class scratchpad {

    public static void main(String[] args) {
        int loanTerm = 360;
        double monthlyPayment = 700.00;
        double interestRateAsPercentage = 6.14;
        double interestRatePercentagePerMonth = (interestRateAsPercentage / 100) / 12.00;

        // math calculation courtesy of https://www.calculatorsoup.com/calculators/financial/loan-calculator.php
        int loanAmount = (int)((monthlyPayment / interestRatePercentagePerMonth) *
                (1 - (1 / Math.pow(1 + interestRatePercentagePerMonth, loanTerm))));

        System.out.println(loanAmount);
    }
}

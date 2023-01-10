package src.loans;

import src.customers.Customers;

public class LoanSuper {
    //Mandatory fields required whenever building/importing a new loan program
    protected int loanID;
    protected int maxLoanSize;
    protected int minLoanSize;
    protected double DTI;
    protected String creditScore;
}

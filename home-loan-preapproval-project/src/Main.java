package src;

import src.customers.Customers;

public class Main {
    public static void main(String[] args){

        Customers newCustomer = new Customers(1,
                "Ted",
                "Mackowski",
                "testemail@gmail.com",
                "123",
                "Merry Lane",
                "Columbus",
                "OH",
                "43215",
                "3305550000",
                "740",
                100000,
                2000,
                false);


        newCustomer.setVeteran(true);
        newCustomer.calcuateMaxLoanApproval();  //everything but USDA has generated a loan amount

        newCustomer.setYearlyIncome(50000);
        newCustomer.setMonthlyDebtPayments(700);
        newCustomer.calcuateMaxLoanApproval();  // Now USDA works









    }
}

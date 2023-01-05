public class Main {

    public static void main(String[] args) {
        BankAccount testAccountOne = new BankAccount();
        BankAccount testAccountTwo = new BankAccount();

        testAccountOne.withdrawFundsFromAccount(100.0);  // custom error message is produced

        double accountTwoBalance = testAccountTwo.depositFundsIntoAccount(250);
        accountTwoBalance = testAccountTwo.withdrawFundsFromAccount(50);
        System.out.println("The balance for AccountNumberTwo is: " + accountTwoBalance);  // 200

        accountTwoBalance = testAccountTwo.depositFundsIntoAccount(25.25);
        System.out.println("The balance for AccountNumberTwo is: " + accountTwoBalance);  // 225.25

        accountTwoBalance = testAccountTwo.withdrawFundsFromAccount(225.25);
        System.out.println("The balance for AccountNumberTwo is: " + accountTwoBalance);  // 0.00
    }
}
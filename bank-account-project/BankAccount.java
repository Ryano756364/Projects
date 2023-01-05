public class BankAccount {
    private int accountNumber;
    private double accountBalance;
    private String customerName;
    private String email;
    private int phoneNumber;

    public int getAccountNumber(){
        return this.accountNumber;
    }
    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance(){
        return this.accountBalance;
    }
    public void setAccountBalance(double accountBalance){
        this.accountBalance = accountBalance;
    }

    public String getCustomerName(){
        return customerName;
    }
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public int getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public double depositFundsIntoAccount(double amountToDeposit){
        // 'this' is not entirely necessary here because we are not passing in any variables name 'balance' and
        // we are not using any other variable's named 'balance'
        // either way you go, try to be consistent
        setAccountBalance(this.accountBalance + amountToDeposit);
        System.out.println("Deposit of $" + amountToDeposit + "complete.");
        return this.getAccountBalance();
    }

    public double withdrawFundsFromAccount(double amountToWithdraw){
        if (this.accountBalance - amountToWithdraw < 0){
            System.out.println("Invalid amount to withdraw. Please withdraw less to avoid account becoming $0.00");
        } else {
            setAccountBalance(this.accountBalance - amountToWithdraw);
        }
        return this.getAccountBalance();
    }
}

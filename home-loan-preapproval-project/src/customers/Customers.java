package src.customers;

import src.loans.Conventional;
import src.loans.FHA;
import src.loans.USDA;
import src.loans.VA;

public class Customers {

    // *** FIELDS ***
    private final double MONTHLY_INSURANCE_PAYMENT = 100.00;
    private final double MONTHLY_TAX_PAYMENT = 333.33;
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String creditScore;
    private int yearlyIncome;
    private int monthlyDebtPayments;
    private boolean isVeteran;
    private double DTI;


    // *** LOAN OBJECTS ***
    Conventional conventionalObject = new Conventional();
    FHA fhaObject = new FHA();
    VA vaObject = new VA();
    USDA usdaObject = new USDA();


    // *** CONSTRUCTORS ***
    public Customers() {};

    public Customers(int customerId, String firstName, String email, String phoneNumber, int yearlyIncome) {
        this(customerId, firstName, "DefaultLastName", email, "000", "StreetName", "City",
                "State", "00000", phoneNumber, "000", yearlyIncome, 0, false);
    }
    public Customers(int customerId, String firstName, String lastName, String email, String streetNumber, String streetName,
                     String city, String state, String zipCode, String phoneNumber, String creditScore, int yearlyIncome,
                     int monthlyDebtPayments, boolean isVeteran) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.creditScore = creditScore;
        this.yearlyIncome = yearlyIncome;
        this.monthlyDebtPayments = monthlyDebtPayments;
        this.isVeteran = isVeteran;
    }


    // *** CUSTOMER AND LOAN ID SETUP ***
    public int getCustomerId(){
        return this.customerId;
    }

    // executing a new customer ID will automatically create a new loan ID for each loan type
    public void setCustomerId(int customerId){
        this.customerId = customerId;
        conventionalObject.setLoanID(customerId);
        fhaObject.setLoanID(customerId);
        vaObject.setLoanID(customerId);
        usdaObject.setLoanID(customerId);
    }


    // ***CUSTOMER PROFILE DETAILS***
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // future software releases may include the limiting of programs via state as some loan programs are
    // only available in certain states
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    // ***LOAN DETAILS***
    public String getCreditScore() {
        return creditScore;
    }

    // passing in credit score to let loan classes decide if the credit score meets the minimum requirement
    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
        conventionalObject.setCreditScore(creditScore);
        fhaObject.setCreditScore(creditScore);
        vaObject.setCreditScore(creditScore);
        usdaObject.setCreditScore(creditScore);
    }

    // further software releases will allow for more precise input of streams of income
    public int getYearlyIncome() {
        return yearlyIncome;
    }
    public void setYearlyIncome(int yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    // further software releases will allow for more precise input of monthly debts
    // only when monthly debt payments are captured, will DTI calculation kick in since yearly income is a required field
    public int getMonthlyDebtPayments() {
        return monthlyDebtPayments;
    }
    public void setMonthlyDebtPayments(int monthlyDebtPayments) {
        this.monthlyDebtPayments = monthlyDebtPayments;
        calculateDTI(yearlyIncome, monthlyDebtPayments);
    }

    // pertinent to VA loan
    public boolean isVeteran() {
        return isVeteran;
    }
    public void setVeteran(boolean veteran) {
        isVeteran = veteran;
        vaObject.setVeteran(veteran);
    }

    // no setter here because manual input of DTI should not be allowed
    public double getDTI(){
        return this.DTI;
    }


    // *** METHODS ***
    public void calculateDTI(int yearlyIncome, int monthlyDebtPayments){
        double monthlyIncome = yearlyIncome / 12.00;  // okay to hard code here because 12 months in a year should never change...
        DTI = (monthlyDebtPayments/monthlyIncome) * 100.00;
    }
    public void calcuateMaxLoanApproval(){
        System.out.println("Conventional Loan Approval Amount: " +
                conventionalObject.findMaxLoanApprovalAmount(this.yearlyIncome, this.monthlyDebtPayments,
                                                             MONTHLY_INSURANCE_PAYMENT, MONTHLY_TAX_PAYMENT));
        System.out.println("FHA Loan Approval Amount: " +
                fhaObject.findMaxLoanApprovalAmount(this.yearlyIncome, this.monthlyDebtPayments,
                                                    MONTHLY_INSURANCE_PAYMENT, MONTHLY_TAX_PAYMENT));
        System.out.println("VA Loan Approval Amount: " +
                vaObject.findMaxLoanApprovalAmount(this.yearlyIncome, this.monthlyDebtPayments,
                                                   MONTHLY_INSURANCE_PAYMENT, MONTHLY_TAX_PAYMENT));
        System.out.println("USDA Loan Approval Amount: " +
                usdaObject.findMaxLoanApprovalAmount(this.yearlyIncome, this.monthlyDebtPayments,
                                                     MONTHLY_INSURANCE_PAYMENT, MONTHLY_TAX_PAYMENT));
    }

    @Override
    public String toString() {
        return "Customers{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

package com.ryanscompany.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Customer {

    private int customer_id;
    private String firstName;
    private String lastName;
    private String state;
    private BigDecimal incomeYearly;
    private BigDecimal monthlyDebtPayments;
    private BigDecimal creditScore;
    private String applicationDate;

    public Customer() {};

    public Customer(int customer_id, String firstName, String lastName, String state, BigDecimal incomeYearly, BigDecimal monthlyDebtPayments, BigDecimal creditScore, String applicationDate) {
        this.customer_id = customer_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.incomeYearly = incomeYearly;
        this.monthlyDebtPayments = monthlyDebtPayments;
        this.creditScore = creditScore;
        this.applicationDate = applicationDate;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public BigDecimal getMonthlyDebtPayments(){
        return monthlyDebtPayments;
    }
    public void setMonthlyDebtPayments(BigDecimal monthlyDebts){
        this.monthlyDebtPayments = monthlyDebts;
    }

    public BigDecimal getCreditScore(){
        return creditScore;
    }
    public void setCreditScore(BigDecimal creditScore){
        this.creditScore = creditScore;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getIncomeYearly() {
        return incomeYearly;
    }

    public void setIncomeYearly(BigDecimal incomeYearly) {
        this.incomeYearly = incomeYearly;
    }

    public String toString(){
        return firstName + " " + lastName;
    }
}
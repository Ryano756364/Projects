package com.ryanscompany.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan {

    private int loan_id;
    private int customer_id;
    private String program;
    private BigDecimal termYears;
    private BigDecimal loanAmount;
    private BigDecimal dti;
    private LocalDate expiration;

    public Loan() {};

    public Loan(int customer_id, String program, BigDecimal termYears, BigDecimal loanAmount, BigDecimal dti,
                LocalDate expiration) {
        this.customer_id = customer_id;
        this.program = program;
        this.termYears = termYears;
        this.loanAmount = loanAmount;
        this.dti = dti;
        this.expiration = expiration;
    }

    public Loan(int loan_id, int customer_id, String program, BigDecimal termYears, BigDecimal loanAmount, BigDecimal dti,
                LocalDate expiration) {
        this.loan_id = loan_id;
        this.customer_id = customer_id;
        this.program = program;
        this.termYears = termYears;
        this.loanAmount = loanAmount;
        this.dti = dti;
        this.expiration = expiration;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public BigDecimal getTermYears() {
        return termYears;
    }

    public void setTermYears(BigDecimal termYears) {
        this.termYears = termYears;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getDti() {
        return dti;
    }

    public void setDti(BigDecimal dti) {
        this.dti = dti;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
}
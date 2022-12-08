package com.ryanscompany.dao;

import com.ryanscompany.model.Loan;
import java.util.List;

public interface LoanDao {
    Loan getLoanById(int loanId);
    List<Loan> getLoans();
    List<Loan> getLoansByCustomerId(int customerId);
    void deleteLoan(int loanId);
}
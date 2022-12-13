package com.ryanscompany.dao;

import com.ryanscompany.model.Loan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcLoanDao implements LoanDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcLoanDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Loan getLoanById(int loanId) {
        Loan loan = null;

        String sql = "SELECT loan_id, customer_id, program, term_years, loan_amount, dti, expiration " +
                "FROM loan " +
                "WHERE loan_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, loanId);
        if (results.next()){
            loan = mapRowToLoan(results);
        }

        return loan;
    }

    @Override
    public List<Loan> getLoans() {
        List<Loan> listOfLoans = new ArrayList<>();

        String sql = "SELECT l.loan_id, l.customer_id, l.program, l.term_years, l.loan_amount, l.dti, l.expiration " +
                "FROM loan AS l " +
                "JOIN customer AS c ON c.customer_id = l.customer_id " +
                "ORDER BY c.customer_id;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        while(sqlRowSet.next()){
            Loan loan = mapRowToLoan(sqlRowSet);
            listOfLoans.add(loan);
        }

        return listOfLoans;
    }

    @Override
    public List<Loan> getLoansByCustomerId(int customerId) {
        List<Loan> listOfLoans = new ArrayList<>();

        String sql = "SELECT l.loan_id, l.customer_id, l.program, l.term_years, l.loan_amount, l.dti, l.expiration " +
                "FROM loan AS l " +
                "JOIN customer AS c ON c.customer_id = l.customer_id " +
                "WHERE l.customer_id = ? " +
                "ORDER BY c.customer_id;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, customerId);

        while(sqlRowSet.next()){
            Loan loan = mapRowToLoan(sqlRowSet);
            listOfLoans.add(loan);
        }

        return listOfLoans;
    }

    @Override
    public Loan createLoan(Loan newLoan) {
        String sql = "INSERT INTO loan (customer_id, program, term_years, loan_amount, dti, expiration) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING loan_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newLoan.getCustomer_id(), newLoan.getProgram(),
                newLoan.getTermYears(), newLoan.getLoanAmount(), newLoan.getDti(), newLoan.getExpiration());
        return getLoanById(newId);
    }

    @Override
    public void deleteLoan(int loanId) {
        String sql = "DELETE FROM loan " +
                "WHERE loan_id = ?;";

        jdbcTemplate.update(sql, loanId);
    }

    private Loan mapRowToLoan(SqlRowSet results){
        Loan loan = new Loan();

        loan.setLoan_id(results.getInt("loan_id"));
        loan.setCustomer_id(results.getInt("customer_id"));
        loan.setProgram(results.getString("program"));
        loan.setTermYears(results.getInt("term_years"));
        loan.setLoanAmount(results.getInt("loan_amount"));
        loan.setDti(results.getBigDecimal("dti"));
        if (results.getDate("expiration") != null) {
            loan.setExpiration(results.getDate("expiration").toLocalDate());
        }

        return loan;
    }
}
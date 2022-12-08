package com.ryanscompany.dao;

import com.ryanscompany.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcCustomerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomer(int customerId) {
        Customer customer = null;
        String sql = "SELECT customer_id, first_name, last_name, income_yearly, state, monthly_debts, credit_score, application_date " +
                "FROM customer " +
                "WHERE customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        if (results.next()) {
            customer = mapRowToCustomer(results);
        }
        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, income_yearly, state, monthly_debts, credit_score, application_date " +
                "FROM customer " +
                "ORDER BY customer_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Customer customer = mapRowToCustomer(results);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Customer createCustomer(Customer newCustomer) {
        String sql = "INSERT INTO customer (first_name, last_name, state, income_yearly, monthly_debts, credit_score, application_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING customer_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newCustomer.getFirstName(), newCustomer.getLastName(),
                newCustomer.getState(), newCustomer.getIncomeYearly(), newCustomer.getMonthlyDebtPayments(),
                newCustomer.getCreditScore(), newCustomer.getApplicationDate());
        return getCustomer(newId);
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {
        String sql = "UPDATE customer " +
                "SET first_name = ?, last_name = ?, state = ?, income_yearly = ?, monthly_debts = ?, credit_score = ?, application_date = ? " +
                "WHERE customer_id = ?";
        jdbcTemplate.update(sql, updatedCustomer.getFirstName(), updatedCustomer.getLastName(), updatedCustomer.getState(),
                updatedCustomer.getIncomeYearly(), updatedCustomer.getMonthlyDebtPayments(), updatedCustomer.getCreditScore(),
                updatedCustomer.getApplicationDate(), updatedCustomer.getCustomer_id());
    }

    @Override
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        jdbcTemplate.update(sql, customerId);
    }

    @Override
    public List<Customer> getCustomersWithNoLoans() {
        List<Customer> listOfCustomersWithNoLoans = new ArrayList<>();

        String sql = "SELECT c.customer_id, c.first_name, c.last_name, c.state, c.income_yearly, c.monthly_debts, c.credit_score, c.application_date " +
                "FROM customer AS c " +
                "LEFT OUTER JOIN loan AS l ON l.customer_id = c.customer_id " +
                "WHERE l.loan_id IS NULL";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()){
            Customer customer = mapRowToCustomer(results);
            listOfCustomersWithNoLoans.add(customer);
        }

        return listOfCustomersWithNoLoans;
    }

    private Customer mapRowToCustomer(SqlRowSet results) {
        // Created a model object to fill in its properties with values from the results set.
        Customer customer = new Customer();

        customer.setCustomer_id(results.getInt("customer_id"));
        customer.setFirstName(results.getString("first_name"));
        customer.setLastName(results.getString("last_name"));
        customer.setState(results.getString("state"));
        customer.setIncomeYearly(results.getBigDecimal("income_yearly"));
        customer.setMonthlyDebtPayments(results.getBigDecimal("monthly_debts"));
        customer.setCreditScore(results.getBigDecimal("credit_score"));
        customer.setApplicationDate(results.getString("application_date"));

        return customer;
    }
}
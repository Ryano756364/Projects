package com.ryanscompany.dao;

import com.ryanscompany.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer getCustomer(int customerId);
    List<Customer> getCustomers();
    Customer createCustomer(Customer newCustomer);
    void updateCustomer(Customer updatedCustomer);
    void deleteCustomer(int customerId);
    List<Customer> getCustomersWithNoLoans();
}
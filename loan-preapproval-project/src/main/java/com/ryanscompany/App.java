package com.ryanscompany;


import com.ryanscompany.dao.CustomerDao;
import com.ryanscompany.dao.JdbcCustomerDao;
import com.ryanscompany.dao.JdbcLoanDao;
import com.ryanscompany.dao.LoanDao;
import org.apache.commons.dbcp2.BasicDataSource;
import util.SystemInOutConsole;

public class App {

    public static void main(String[] args){

        // Create the datasource used by all the DAO's
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/loancompany");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");

        // Controller expects the DAOs it needs to be "injected" in the constructor.
        // Create the DAOs needed by the controller.
        CustomerDao customerDao = new JdbcCustomerDao(dataSource);
        LoanDao loanDao = new JdbcLoanDao(dataSource);

        // Create the basic i/o mechanism (the console)
        SystemInOutConsole systemInOutConsole = new SystemInOutConsole();

        // The controller manages the program flow. Create a control and call its run() method to start the menu loop.
        RyansCompanyController controller = new RyansCompanyController(systemInOutConsole, customerDao,
                loanDao);
        controller.run();
    }
}
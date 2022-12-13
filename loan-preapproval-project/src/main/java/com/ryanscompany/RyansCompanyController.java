package com.ryanscompany;

import com.ryanscompany.dao.CustomerDao;
import com.ryanscompany.dao.LoanDao;
import com.ryanscompany.model.Customer;
import com.ryanscompany.model.Loan;
import util.BasicConsole;

import java.util.List;

//The controller is operated through various menus.
public class RyansCompanyController {

    // View manages all the user interaction, inputs, and outputs
    private final RyansCompanyView view;
    private CustomerDao customerDao;
    private LoanDao loanDao;
    private LoanLogic loanLogic;

    public RyansCompanyController(BasicConsole console, CustomerDao customerDao, LoanDao loanDao) {
        view = new RyansCompanyView(console);
        this.customerDao = customerDao;
        this.loanDao = loanDao;
    }

    public void run() {
        displayMainMenu();
    }

    //Main menu
    private void displayMainMenu() {

        // Menu options
        final String CUSTOMER_MENU = "Customer Admin Menu";
        final String LOAN_MENU = "Loan Admin Menu";
        final String EXIT = "Exit the Program";
        final String[] MENU_OPTIONS = {CUSTOMER_MENU, LOAN_MENU, EXIT};

        boolean finished = false;

        // The menu loop
        while (!finished) {
            String selection =
                    view.getMenuSelection("Welcome to The Fake Mortgage Lender Company", MENU_OPTIONS);
            switch (selection) {
                case CUSTOMER_MENU:
                    displayCustomerMenu();
                    break;
                case LOAN_MENU:
                    displayLoanMenu();
                    break;
                case EXIT:
                    // Set finished to true so the loop exits.
                    finished = true;
                    break;
            }
        }
    }

    //Customer menu
    private void displayCustomerMenu(){

        //Menu options
        final String CUSTOMER_LIST = "List all current customers";
        final String CUSTOMER_DETAILS = "View customer details";
        final String CUSTOMER_ADD = "Add a new customer";
        final String CUSTOMER_UPDATE = "Update customer details";
        final String CUSTOMER_DELETE = "Delete a customer";
        final String MAIN_MENU = "Main menu";
        final String[] MENU_OPTIONS = {
                CUSTOMER_LIST,
                CUSTOMER_DETAILS,
                CUSTOMER_ADD,
                CUSTOMER_UPDATE,
                CUSTOMER_DELETE,
                MAIN_MENU
        };

        boolean finished = false;

        //Menu loop
        while (!finished){
            String selection = view.getMenuSelection("Customer admin menu", MENU_OPTIONS);
            switch (selection){
                case CUSTOMER_LIST:
                    listCustomers();
                    break;
                case CUSTOMER_DETAILS:
                    displayCustomerDetails();
                    break;
                case CUSTOMER_ADD:
                    addNewCustomer();
                    break;
                case CUSTOMER_UPDATE:
                    updateCustomerInfo();
                    break;
                case CUSTOMER_DELETE:
                    deleteCustomer();
                    break;
                case MAIN_MENU:
                    finished = true;
            }
        }
    }

    //Loan list menu
    private void displayLoanMenu(){

        //Menu options
        final String LOAN_LIST = "List all loans";
        final String LOAN_DETAILS_BY_CUSTOMER = "View loan details by customer";
        final String LOAN_DELETE = "Delete loan";
        final String MAIN_MENU = "Main menu";
        final String[] MENU_OPTIONS = {
                LOAN_LIST,
                LOAN_DETAILS_BY_CUSTOMER,
                LOAN_DELETE,
                MAIN_MENU
        };

        boolean finished = false;

        //Menu loop
        while (!finished) {
            String selection = view.getMenuSelection("Loan admin menu", MENU_OPTIONS);
            switch (selection) {
                case LOAN_LIST:
                    listLoans();
                    break;
                case LOAN_DETAILS_BY_CUSTOMER:
                    displayLoanByCustomerId();
                    break;
                case LOAN_DELETE:
                    deleteLoan();
                    break;
                case MAIN_MENU:
                    finished = true;
                    break;
            }
        }
    }

    //Customer loan menu choices
    private void listCustomers() {

        //Making sure we have the right DAO's
        if (customerDao == null){
            view.printErrorMessage("You must create a CusotmerDao and pass it into controller");
            return;
        }

        //Using Dao to get list of customers
        List<Customer> customers = customerDao.getCustomers();
        view.printCustomerList(customers);
    }
    private void displayCustomerDetails() {
        if (customerDao == null){
            view.printErrorMessage("You must create a CusotmerDao and pass it into controller");
            return;
        }

        //Gets list of customers so user can choose one
        List<Customer> customers = customerDao.getCustomers();

        //Displays list of customers and asks for a choice
        Customer customer = view.selectCustomer(customers);
        if (customer == null){
            return;
        }
        view.printCustomerDetail(customer);
    }
    private void addNewCustomer(){
        if (customerDao == null){
            view.printErrorMessage("You must create a CusotmerDao and pass it into controller");
            return;
        }

        //Asks for new customer info
        Customer newCustomer = view.promptForCustomerInformation(null);
        if (newCustomer == null){
            return;
        }
        newCustomer = customerDao.createCustomer(newCustomer);

        loanLogic.checkForConventional(newCustomer);
        loanLogic.checkForFHA(newCustomer);

        view.printMessage(newCustomer.getFirstName() + " " + newCustomer.getLastName() + "'s profile has been created.");
    }

    private void updateCustomerInfo(){
        if (customerDao == null){
            view.printErrorMessage("You must create a CusotmerDao and pass it into controller");
            return;
        }

        List<Customer> customers = customerDao.getCustomers();
        Customer customer = view.selectCustomer(customers);
        if (customer == null){
            return;
        }
        customer = view.promptForCustomerInformation(customer);

        customerDao.updateCustomer(customer);
        view.printMessage(customer.getFirstName() + " " + customer.getLastName() + "'s profile has been updated.");
    }
    private void deleteCustomer(){
        if (customerDao == null){
            view.printErrorMessage("You must create a CusotmerDao and pass it into controller");
            return;
        }

        //No loans due to foreign key constraints
        List<Customer> customers = customerDao.getCustomersWithNoLoans();
        if (customers.size() == 0){
            view.printMessage("There are no customer profiles that may be deleted!");
        }
        Customer customer = view.selectCustomer(customers);
        if (customer == null){
            return;
        }

        boolean isConfirmed = view.promptForYesNo("Are you sure you want to delete this customer: " +
                customer.getFirstName() + " " + customer.getLastName());
        if (!isConfirmed){
            return;
        }

        //Proceeding with deletion
        String fullName = customer.getFirstName() + " " + customer.getLastName();
        customerDao.deleteCustomer(customer.getCustomer_id());
        view.printMessage(fullName + "'s profile has been deleted");
    }

    //Loan menu choices
    private void listLoans() {
        if (loanDao == null) {
            view.printErrorMessage("You must implement LoanDao and pass it into the controller for this to work");
            return;
        }

        List<Loan> loans = loanDao.getLoans();
        view.printLoanList(loans);
    }
    private void displayLoanByCustomerId(){
        List<Customer> customers = customerDao.getCustomers();

        Customer customer = view.selectCustomer(customers);
        if (customer == null){
            return;
        }

        if (loanDao == null) {
            view.printErrorMessage("You must implement LoanDao and pass it into the controller for this to work");
            return;
        }

        List<Loan> loans = loanDao.getLoansByCustomerId(customer.getCustomer_id());
        if (loans.size() == 0) {
            view.printErrorMessage("There are no loans for this customer!");
        }

        //Show list of loans
        Loan selectedLoan = view.selectLoan(loans);
        if(selectedLoan == null){
            return;
        }
        view.printLoanDetail(selectedLoan);
    }
    private void deleteLoan(){
        if (loanDao == null){
            view.printErrorMessage("You must implement LoanDao and pass it into the controller for this to work");
            return;
        }

        //Check if there are loans available
        List<Loan> loans = loanDao.getLoans();
        if (loans.size() == 0){
            view.printErrorMessage("There are no loans available to delete");
        }

        //Display loans to delete
        Loan loan = view.selectLoan(loans);
        if (loan == null){
            return;
        }

        //Confirmation
        boolean isConfirmed = view.promptForYesNo("Are you sure you want to delete the loan for id: " + loan.getLoan_id() + " [y/n]");
        if (!isConfirmed){
            return;
        }

        loanDao.deleteLoan(loan.getLoan_id());
    }
}
package com.ryanscompany;

import com.ryanscompany.model.Customer;
import com.ryanscompany.model.Loan;
import util.BasicConsole;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class RyansCompanyView {
    private final BasicConsole console;

    public RyansCompanyView(BasicConsole console) {
        this.console = console;
    }

    // printMessage passes call through to console
    public void printMessage(String message) {
        console.printMessage(message);
    }

    public void printErrorMessage(String message) {
        console.printErrorMessage(message);
    }

    public void printBanner(String message) {
        console.printBanner(message);
    }

    // promptForYesNo passes call through to console
    public boolean promptForYesNo(String prompt) {
        return console.promptForYesNo(prompt);
    }

    public String getMenuSelection(String menuTitle, String[] options) {
        printBanner(menuTitle);
        return console.getMenuSelection(options);
    }

    public void listLoans(List<Loan> loans) {
        printBanner("All Available Loans");
        printLoanList(loans);
        console.stopOutput();
    }


    //Printing lists of customers and loans
    public void printCustomerList(List<Customer> customers) {
        String heading1 = "  Id FirstName                 LastName                  ST";
        String heading2 = "---- ------------------------- ------------------------- --";
        String formatString = "%-4d %-25s %-25s %2s";
        printMessage(heading1);
        printMessage(heading2);
        for (Customer customer : customers) {
            String s = String.format(formatString,
                    customer.getCustomer_id(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getState()
            );
            printMessage(s);
        }
    }

    public void printLoanList(List<Loan> loans) {
        String heading1 = "Id    C.ID Program         Term(Y)    Loan Amount";
        String heading2 = "---- ----- --------------- ------- --------------";
        String formatString = "%-4d %-5d %-15s %4f %14f";
        printMessage(heading1);
        printMessage(heading2);
        for (Loan loan : loans) {
            String s = String.format(formatString,
                    loan.getLoan_id(),
                    loan.getCustomer_id(),
                    loan.getProgram(),
                    loan.getTermYears().doubleValue(),
                    loan.getLoanAmount().doubleValue()
            );
            printMessage(s);
        }
    }


    //Ask user to select menu option
    public Customer selectCustomer(List<Customer> customers) {
        while (true) {
            printCustomerList(customers);
            Integer customerId = console.promptForInteger("Enter customer id [0 to cancel]: ");
            if (customerId == null || customerId == 0) {
                return null;
            }
            Customer selectedCustomer = findCustomerById(customers, customerId);
            if (selectedCustomer != null) {
                return selectedCustomer;
            }
            printErrorMessage("That's not a valid id, please try again.");
        }
    }

    public Loan selectLoan(List<Loan> loans) {
        while (true) {
            printLoanList(loans);
            Integer loanId = console.promptForInteger("Enter product id [0 to cancel]: ");
            if (loanId == null || loanId == 0) {
                return null;
            }
            Loan chosenLoan = findLoanById(loans, loanId);
            if (chosenLoan != null) {
                return chosenLoan;
            }
            printErrorMessage("That's not a valid id, please try again.");
        }
    }

    private Customer findCustomerById(List<Customer> customers, int customerid) {
        for (Customer customer : customers) {
            if (customer.getCustomer_id() == customerid) {
                return customer;
            }
        }
        return null;
    }

    private Loan findLoanById(List<Loan> loans, int loanId) {
        for (Loan loan : loans) {
            if (loan.getLoan_id() == loanId) {
                return loan;
            }
        }
        return null;
    }


    //Print more detail for a selected loan and customer
    public void printLoanDetail(Loan loan){
        printMessage(String.format("Loan id: %s", loan.getLoan_id()));
        printMessage(String.format("Customer id: %s", loan.getCustomer_id()));
        printMessage(String.format("Program name: %s", loan.getProgram()));
        printMessage(String.format("Loan term in years: %s", loan.getTermYears()));
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
        printMessage(String.format("Loan amount: %s", moneyFormat.format(loan.getLoanAmount())));
        printMessage(String.format("Customer DTI: %s", loan.getDti()));
        DateTimeFormatter dateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        printMessage(String.format("PreApproval expiration date: %s", dateTime.format(loan.getExpiration())));

        console.stopOutput();
    }

    public void printCustomerDetail(Customer customer){
        printMessage(String.format("Customer Last Name, First Name: %s", customer.getLastName() + ", " +
                customer.getFirstName()));
        printMessage(String.format("Customer id: %s", customer.getCustomer_id()));
        printMessage(String.format("Customer state: %s", customer.getState()));
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
        printMessage(String.format("Customer yearly income: %s", moneyFormat.format(customer.getIncomeYearly())));
        printMessage(String.format("Customer monthly debt obligations: %s", moneyFormat.format(customer.getMonthlyDebtPayments())));
        printMessage(String.format("Customer credit score: %s", customer.getCreditScore()));
        printMessage(String.format("Customer application date: %s", customer.getApplicationDate()));

        console.stopOutput();
    }

    //Prompt to update/new customer
    public Customer promptForCustomerInformation(Customer existingCustomer){
        Customer newCustomer = new Customer();
        if (existingCustomer == null){
            //No default values, ID will be created automatically
            newCustomer.setFirstName(promptForFirstName(null));
            newCustomer.setLastName(promptForLastName(null));
            newCustomer.setState(promptForState(null));
            newCustomer.setIncomeYearly(promptForIncomeYearly(null));
            newCustomer.setMonthlyDebtPayments(promptForMonthlyDebt(null));
            newCustomer.setCreditScore(promptForCreditScore(null));
            newCustomer.setApplicationDate(promtForApplicationDate(null));
        } else {
            //Update values
            newCustomer.setCustomer_id(existingCustomer.getCustomer_id());
            newCustomer.setFirstName(existingCustomer.getFirstName());
            newCustomer.setLastName(existingCustomer.getLastName());
            newCustomer.setState(existingCustomer.getState());
            newCustomer.setIncomeYearly(existingCustomer.getIncomeYearly());
            newCustomer.setMonthlyDebtPayments(existingCustomer.getMonthlyDebtPayments());
            newCustomer.setCreditScore(existingCustomer.getCreditScore());
            newCustomer.setApplicationDate(existingCustomer.getApplicationDate());
        }
        
        return newCustomer;
    }

    private String promptForFirstName(String defaultValue) {
        return promptForString("Customer first name: ", true, defaultValue);
    }

    private String promptForLastName(String defaultValue) {
        return promptForString("Customer last name: ", true, defaultValue);
    }

    private String promptForState(String defaultValue) {
        return promptForString("Customer state residence: ", true, defaultValue);
    }

    private BigDecimal promptForIncomeYearly(BigDecimal defaultValue) {
        return promptForBigDecimal("Customer yearly income: ", true, defaultValue);
    }

    private BigDecimal promptForMonthlyDebt(BigDecimal defaultValue) {
        return promptForBigDecimal("Cumulative minimum monthly debt obligations: ", true, defaultValue);
    }

    private BigDecimal promptForCreditScore(BigDecimal defaultValue) {
        return promptForBigDecimal("Customer credit score: ", true, defaultValue);
    }

    private String promtForApplicationDate(String defaultValue){
        return promptForString("Enter today's date in YYYY-MM-DD format and hit enter: ", true, defaultValue);
    }

    private String promptForString(String prompt, boolean required, String defaultValue) {
        prompt = promptWithDefault(prompt, defaultValue);
        while (true) {
            String entry = console.promptForString(prompt);
            if (!entry.isEmpty()) {
                return entry;
            }
            // Entry is empty: see if we have a default, or if empty is OK (!required)
            if (defaultValue != null && !defaultValue.isEmpty()) {
                return defaultValue;
            }
            if (!required) {
                return entry;
            }
            printErrorMessage("A value is required, please try again.");
        }
    }

    private String promptWithDefault(String prompt, Object defaultValue) {
        if (defaultValue != null) {
            return prompt + "[" + defaultValue.toString() + "]: ";
        }
        return prompt + ": ";
    }

    private BigDecimal promptForBigDecimal(String prompt, boolean required, BigDecimal defaultValue) {
        prompt = promptWithDefault(prompt, defaultValue);
        while (true) {
            BigDecimal entry = console.promptForBigDecimal(prompt);
            if (entry != null) {
                return entry;
            }
            // Entry is empty: see if we have a default, or if empty is OK (!required)
            if (defaultValue != null) {
                return defaultValue;
            }
            if (!required) {
                return BigDecimal.valueOf(0.0);
            }
            printErrorMessage("A value is required, please try again.");
        }
    }

}
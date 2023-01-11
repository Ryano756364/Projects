package src;

import src.util.BasicConsole;

import java.util.List;

public class View {

    // *** FIELDS ***
    private final BasicConsole console;

    public View(BasicConsole console) {
        this.console = console;
    }


    // *** printMessage passes call through to console ***
    public void printMessage(String message) {
        console.printMessage(message);
    }

    public void printErrorMessage(String message) {
        console.printErrorMessage(message);
    }

    public void printBanner(String message) {
        console.printBanner(message);
    }

    // *** promptForYesNo passes call through to console ***
    public boolean promptForYesNo(String prompt) {
        return console.promptForYesNo(prompt);
    }

    public String getMenuSelection(String menuTitle, String[] options) {
        printBanner(menuTitle);
        return console.getMenuSelection(options);
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
}

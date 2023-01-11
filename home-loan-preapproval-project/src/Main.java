package src;

import src.customers.Customers;
import src.util.SystemInOutConsole;

public class Main {
    public static void main(String[] args){

        // Create the basic i/o mechanism (the console)
        SystemInOutConsole systemInOutConsole = new SystemInOutConsole();

        // Controller managing the program flow. Run will start the loop.
        Controller controller = new Controller(systemInOutConsole);
        controller.run();

    }
}

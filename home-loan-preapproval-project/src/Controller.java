package src;

import src.util.BasicConsole;

public class Controller {
    // *** Fields ***
    private final LoanCompany view;

    public Controller(BasicConsole console){
        view = new LoanCompany(console);
    }

}

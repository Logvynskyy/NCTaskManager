package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.view.HelpMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class Help implements Action {
    @Override
    public View perform() {
        return new HelpMessage();
    }
}

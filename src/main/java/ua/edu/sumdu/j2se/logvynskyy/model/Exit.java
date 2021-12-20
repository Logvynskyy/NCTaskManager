package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.view.ExitMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class Exit implements Action{
    @Override
    public View perform() {
        return new ExitMessage();
    }
}

package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.view.HelpMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class Help implements Action {
    private static final Logger logger = Logger.getLogger(Help.class);
    @Override
    public View perform() {
        logger.info("Returned a guide to the program");
        return new HelpMessage();
    }
}

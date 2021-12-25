package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.view.ExitMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class Exit implements Action{
    private static final Logger logger = Logger.getLogger(Exit.class);
    @Override
    public View perform() {
        logger.info("Exiting program");
        return new ExitMessage();
    }
}

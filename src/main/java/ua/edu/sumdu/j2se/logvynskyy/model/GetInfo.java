package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.view.InfoMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class GetInfo implements Action{
    private static final Logger logger = Logger.getLogger(GetInfo.class);
    @Override
    public View perform() {
        AbstractTaskList list = ArrayTaskList.getList();

        logger.info("Returned info about task list");
        return new InfoMessage(list.toString());
    }
}

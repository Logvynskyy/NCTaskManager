package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.view.AddMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class AddTask implements Action{
    private static final Logger logger = Logger.getLogger(AddTask.class);

    @Override
    public View perform() {
        AbstractTaskList list = ArrayTaskList.getList();
        list.add((Task) DataFactory.getData(DataType.TASK));

        logger.info("Added new task and wrote info to the file");
        return new AddMessage();
    }
}

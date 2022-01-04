package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.view.ChangeMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.FailMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class ChangeTask implements Action{
    private static final Logger logger = Logger.getLogger(ChangeTask.class);

    @Override
    public View perform() {
        int id = (int) DataFactory.getData(DataType.ID);
        try {
            AbstractTaskList list = ArrayTaskList.getList();
            Task[] tasks = list.getTaskList();
            tasks[id - 1] = (Task) DataFactory.getData(DataType.TASK);
            list.setTaskList(tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            logger.error(e.getCause());
            System.out.println("Невірно заданий номер завдання!");
            return new FailMessage();
        }

        logger.info("Changed parameters of given task");
        return new ChangeMessage();
    }
}

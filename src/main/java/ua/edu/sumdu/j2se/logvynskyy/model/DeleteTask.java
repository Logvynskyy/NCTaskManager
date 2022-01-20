package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.view.DeleteMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.FailMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class DeleteTask implements Action{
    private static final Logger logger = Logger.getLogger(DeleteTask.class);

    @Override
    public View perform() {
        int id = (int) DataFactory.getData(DataType.ID);
        try {
            AbstractTaskList list = ArrayTaskList.getList();
            list.remove(list.getTask(id - 1));
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            logger.error(e.getMessage());
            System.out.println("Невірно заданий номер завдання!");
            return new FailMessage();
        }

        logger.info("Deleted given task");
        return new DeleteMessage();
    }
}

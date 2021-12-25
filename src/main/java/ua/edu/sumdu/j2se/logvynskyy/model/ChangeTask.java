package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.controller.Controller;
import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.GetTaskByName;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.TaskIO;
import ua.edu.sumdu.j2se.logvynskyy.view.ChangeMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.FailMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.io.FileWriter;
import java.io.IOException;

public class ChangeTask implements Action{
    private static final Logger logger = Logger.getLogger(ChangeTask.class);

    @Override
    public View perform() {
        String title = (String) DataFactory.getData(DataType.TITLE);
        try {
            AbstractTaskList list = ArrayTaskList.getList();
            list.remove(GetTaskByName.getTask(title));
            list.add((Task) DataFactory.getData(DataType.TASK));
            TaskIO.write(list, new FileWriter("data.json"));
        } catch (ClassNotFoundException e) {
            logger.error(e.getCause());
            System.out.println("Не знайдено задачу з введеною назвою!");
            return new FailMessage();
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.out.println("Помилка запису до файлу!");
        }

        logger.info("Changed parameters of given task");
        return new ChangeMessage();
    }
}

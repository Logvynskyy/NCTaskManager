package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.GetTaskByName;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.TaskIO;
import ua.edu.sumdu.j2se.logvynskyy.view.DeleteMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.FailMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.io.FileWriter;
import java.io.IOException;

public class DeleteTask implements Action{
    private static final Logger logger = Logger.getLogger(DeleteTask.class);

    @Override
    public View perform() {
        String title = (String) DataFactory.getData(DataType.TITLE);
        try {
            AbstractTaskList list = ArrayTaskList.getList();
            list.remove(GetTaskByName.getTask(title));
            TaskIO.write(list, new FileWriter("data.json"));
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            System.out.println("Не знайдено задачу з введеною назвою!");
            return new FailMessage();
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.out.println("Помилка запису до файлу!");
        }

        logger.info("Deleted given task");
        return new DeleteMessage();
    }
}

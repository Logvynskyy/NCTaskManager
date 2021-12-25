package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.controller.Controller;
import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.GetTaskByName;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.TaskIO;
import ua.edu.sumdu.j2se.logvynskyy.view.ChangeMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.io.FileWriter;
import java.io.IOException;

public class ChangeTask implements Action{
    @Override
    public View perform() {
        String title = (String) DataFactory.getData(DataType.TITLE);
        try {
            AbstractTaskList list = ArrayTaskList.getList();
            list.remove(GetTaskByName.getTask(title));
            list.add((Task) DataFactory.getData(DataType.TASK));
            TaskIO.write(list, new FileWriter("data.json"));
        } catch (ClassNotFoundException e) {
            System.out.println("Не знайдено файлу з введеною назвою. Перевірте та спробуйте ще раз!");
        } catch (IOException e) {
            System.out.println("Помилка запису до файлу!");
        }

        return new ChangeMessage();
    }
}

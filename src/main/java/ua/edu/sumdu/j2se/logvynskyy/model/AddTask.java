package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.TaskIO;
import ua.edu.sumdu.j2se.logvynskyy.view.AddMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.io.FileWriter;
import java.io.IOException;

public class AddTask implements Action{
    @Override
    public View perform() {
        AbstractTaskList list = ArrayTaskList.getList();
        try {
            list.add((Task) DataFactory.getData(DataType.TASK));
            TaskIO.write(list, new FileWriter("data.json"));
        } catch (IOException e) {
            System.out.println("Помилка запису до файлу!");
        }

        return new AddMessage();
    }
}

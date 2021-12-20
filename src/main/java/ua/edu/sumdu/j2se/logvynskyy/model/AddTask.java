package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.controller.Controller;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.TaskIO;
import ua.edu.sumdu.j2se.logvynskyy.view.AddMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AddTask implements Action{
    @Override
    public View perform() {
        Controller controller = Controller.getController();
        AbstractTaskList list = new ArrayTaskList();
        try {
            TaskIO.read(list, new FileReader("data.json"));
            list.add(controller.getTask());
            TaskIO.write(list, new FileWriter("data.json"));
        } catch (IOException e) {
            System.out.println("Помилка запису до файлу!");
        }

        return new AddMessage();
    }
}

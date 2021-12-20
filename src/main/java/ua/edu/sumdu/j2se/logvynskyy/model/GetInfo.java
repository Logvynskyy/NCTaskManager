package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.TaskIO;
import ua.edu.sumdu.j2se.logvynskyy.view.InfoMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.io.FileReader;
import java.io.IOException;

public class GetInfo implements Action{

    @Override
    public View perform() {
        AbstractTaskList list = new ArrayTaskList();
        try {
            TaskIO.read(list, new FileReader("data.json"));
        } catch (IOException e) {
            System.out.println("Помилка зчитування з файлу!");
        }
        return new InfoMessage(list.toString());
    }
}

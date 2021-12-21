package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.controller.Controller;
import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.GetTaskByName;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class ChangeTask implements Action{
    @Override
    public View perform() {
        String title = (String) DataFactory.getData(DataType.TITLE);
        try {
            AbstractTaskList list = ArrayTaskList.getList();
            list.remove(GetTaskByName.getTask(title));
        } catch (ClassNotFoundException e) {
            System.out.println("Не знайдено файлу з введеною назвою. Перевірте та спробуйте ще раз!");
        }

        return null;
    }
}

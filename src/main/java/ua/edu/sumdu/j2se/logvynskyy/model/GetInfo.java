package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.view.InfoMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class GetInfo implements Action{

    @Override
    public View perform() {
        AbstractTaskList list = ArrayTaskList.getList();

        return new InfoMessage(list.toString());
    }
}

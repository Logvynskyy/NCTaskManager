package ua.edu.sumdu.j2se.logvynskyy.model.utils;

import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.LinkedTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ListTypes;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        AbstractTaskList classType = null;
        switch(type){
            case ARRAY:
                classType = new ArrayTaskList();
                break;
            case LINKED:
                classType = new LinkedTaskList();
                break;
        }
        return classType;
    }
}

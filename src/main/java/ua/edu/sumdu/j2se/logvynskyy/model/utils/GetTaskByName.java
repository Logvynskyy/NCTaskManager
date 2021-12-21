package ua.edu.sumdu.j2se.logvynskyy.model.utils;

import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;

public class GetTaskByName {
    public static Task getTask(String name) throws ClassNotFoundException {
        AbstractTaskList list = ArrayTaskList.getList();
        Task task = null;
        for(Task t : list){
            if(t.getTitle().equals(name))
                task = t;
        }
        if(task == null)
            throw new ClassNotFoundException();
        return task;
    }
}

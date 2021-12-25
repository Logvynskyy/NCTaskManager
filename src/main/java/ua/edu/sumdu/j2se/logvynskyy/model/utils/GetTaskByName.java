package ua.edu.sumdu.j2se.logvynskyy.model.utils;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;

public class GetTaskByName {
    private static final Logger logger = Logger.getLogger(GetTaskByName.class);

    public static Task getTask(String name) throws ClassNotFoundException {
        AbstractTaskList list = ArrayTaskList.getList();
        Task task = null;
        for(Task t : list){
            if(t.getTitle().equals(name))
                task = t;
        }
        if(task == null) {
            logger.error("Task with given title doesn't exist");
            throw new ClassNotFoundException();
        }
        logger.info("Returned a task by given title");
        return task;
    }
}

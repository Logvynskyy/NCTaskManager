package ua.edu.sumdu.j2se.logvynskyy.model.utils;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;

import java.util.Arrays;
import java.util.List;

public class GetTaskByID {
    private static final Logger logger = Logger.getLogger(GetTaskByID.class);

    public static Task getTask(int id) throws ClassNotFoundException {
        AbstractTaskList list = ArrayTaskList.getList();

        List<Object> list1 = Arrays.asList(list.getStream().toArray());
//        for(Task t : list){
//            if(t.getTitle().equals(id))
//                task = t;
//        }
        Task task = list.getTask(id);
        if(task == null) {
            logger.error("Task with given title doesn't exist");
            throw new ClassNotFoundException();
        }
        logger.info("Returned a task by given title");
        return task;
    }
}

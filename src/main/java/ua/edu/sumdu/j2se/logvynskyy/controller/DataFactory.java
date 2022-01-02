package ua.edu.sumdu.j2se.logvynskyy.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.view.GetTaskInfo;

import java.time.LocalDateTime;

public class DataFactory {
    private static final Logger logger = Logger.getLogger(DataFactory.class);

    public static Object getData(DataType type) {
        GetTaskInfo info = new GetTaskInfo();
        Object data = null;
        switch (type){
            case TITLE:
                data = info.getTitle();
                break;
            case START_TIME:
                data = info.getStartTime();
                break;
            case END_TIME:
                data = info.getEndTime();
                break;
            case IS_REPEATED:
                data = info.isRepeated();
                break;
            case INTERVAL:
                data = info.getInterval();
                break;
            case IS_ACTIVE:
                data = info.isActive();
                break;
            case ID:
                data = info.getID();
                break;
            case TASK:
                Task task;
                if(!info.isRepeated()) {
                    String title = info.getTitle();
                    LocalDateTime startTime = info.getStartTime();
                    task = new Task(title, startTime);
                }
                else{
                    String title = info.getTitle();
                    LocalDateTime startTime = info.getStartTime();
                    LocalDateTime endTime = info.getEndTime();
                    int interval = info.getInterval();
                    task = new Task(title, startTime, endTime, interval);
                }
                data = task;
                break;
        }
        if(data == null){
            logger.error("Requested data type that doesn't exist");
            throw new ClassCastException();
        }
        logger.info("Returned requested data type - " + type.name());
        return data;
    }
}

package ua.edu.sumdu.j2se.logvynskyy.controller;

import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.view.GetTaskInfo;

import java.time.LocalDateTime;

public class DataFactory {
    public static Object getData(DataType type){
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
        return data;
    }
}

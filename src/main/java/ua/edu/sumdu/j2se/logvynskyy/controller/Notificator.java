package ua.edu.sumdu.j2se.logvynskyy.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.view.NotificationMessage;

import java.time.LocalDateTime;
import java.util.*;

public class Notificator extends Thread {
    private static final Logger logger = Logger.getLogger(Notificator.class);
    private StringBuilder sb = new StringBuilder();
    private LocalDateTime curTime = LocalDateTime.now();

    @Override
    public void run() {
        while(true) {
            AbstractTaskList list = ArrayTaskList.getList();

            this.curTime = LocalDateTime.now();
            Task[] tasks = Arrays.stream(list.getStream().toArray(Task[]::new))
                    .filter(task -> task.nextTimeAfter(curTime) != null)
                    .toArray(Task[]::new);

            Comparator<Task> comparatorByTime = Comparator.comparing(o -> o.nextTimeAfter(curTime));
            Arrays.sort(tasks, comparatorByTime);

            for(Task task : tasks){
                LocalDateTime time = task.nextTimeAfter(curTime);
                while(!time.isAfter(curTime)){
                    if(time.isEqual(curTime)){
                        logger.info("Notificator worked for time " + curTime);
                        sb.append("Задача ").append(task.getTitle()).append(" повинна виконатися зараз!");
                        NotificationMessage nm = new NotificationMessage(sb.toString());
                        nm.getMessage();
                    }
                }
            }

            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
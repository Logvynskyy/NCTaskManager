package ua.edu.sumdu.j2se.logvynskyy.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.view.NotificationMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Notificator extends Thread {
    private static final Logger logger = Logger.getLogger(Notificator.class);
    private String notification = "";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private LocalDateTime curTime = LocalDateTime.parse(LocalDateTime.now().format(formatter));

    @Override
    public void run() {
        while(true) {
            AbstractTaskList list = ArrayTaskList.getList();
            Task[] times = Arrays.stream(list.getStream().distinct()
                    .toArray(Task[]::new))
                    .filter(task -> task.nextTimeAfter(curTime) != null && task.isActive())
                    .toArray(Task[]::new);

            Comparator<Task> comparatorByTime = Comparator.comparing(o -> o.nextTimeAfter(curTime));
            Arrays.sort(times, comparatorByTime);

            if(times.length != 0){
                LocalDateTime time = times[0].nextTimeAfter(curTime);
                while(time.compareTo(curTime) > -1){
//                    System.out.println("1");
                    this.curTime = LocalDateTime.parse(LocalDateTime.now().format(formatter));
                    if(time.equals(curTime)){
//                        System.out.println("EQUALS!");
                        logger.info("Notificator worked for time " + curTime);
                        notification = "Задача " + times[0].getTitle() + " повинна виконатися зараз!";
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                NotificationMessage nm = new NotificationMessage(notification);
                nm.getMessage();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
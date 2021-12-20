package ua.edu.sumdu.j2se.logvynskyy.controller;

import ua.edu.sumdu.j2se.logvynskyy.model.Action;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.view.GetTaskInfo;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.time.LocalDateTime;

public class Controller {
    private static Controller controller = new Controller();

    private Controller() {}

    public static Controller getController() {
        if (controller ==null) {
            controller = new Controller();
        }
        return controller;
    }

    public void getAction(int num) {
        if(num > 7 || num < 1)
            throw new IllegalArgumentException("Введіть коректний номер задачі!");
        Menu menu = Menu.getMenu();
        Action action = menu.getAction(num);
        View page = action.perform();
        page.getMessage();
    }

    public Task getTask(){
        GetTaskInfo info = new GetTaskInfo();
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
        return task;
    }
}

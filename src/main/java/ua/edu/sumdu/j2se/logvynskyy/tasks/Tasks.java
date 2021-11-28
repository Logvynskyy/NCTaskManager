package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException {
        if(end.isBefore(start)) throw new IllegalArgumentException("Невірні параметри часу!");

        AbstractTaskList plannedTasks = TaskListFactory.createTaskList(((AbstractTaskList) tasks).getInstance());
        ((AbstractTaskList) tasks).getStream()
                .distinct()
                .filter(task -> task.nextTimeAfter(start) != null && task.nextTimeAfter(start).isBefore(end))
                .forEach(plannedTasks::add);
        return plannedTasks;
    }
}

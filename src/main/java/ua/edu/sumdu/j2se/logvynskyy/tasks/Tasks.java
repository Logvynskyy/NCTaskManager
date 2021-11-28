package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.StreamSupport;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException {
        if(tasks == null || start == null || end == null) throw new IllegalArgumentException("Список не повинен бути пустим!");
        if(end.isBefore(start)) throw new IllegalArgumentException("Невірні параметри часу!");

        AbstractTaskList plannedTasks = TaskListFactory.createTaskList(((AbstractTaskList) tasks).getInstance());
        ((AbstractTaskList) tasks).getStream()
                .distinct()
                .filter(task -> task.nextTimeAfter(start) != null && task.nextTimeAfter(start).isBefore(end))
                .forEach(plannedTasks::add);
        return plannedTasks;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException {
        if(tasks == null || start == null || end == null) throw new IllegalArgumentException("Список не повинен бути пустим!");
        if(end.isBefore(start)) throw new IllegalArgumentException("Невірні параметри часу!");

        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        Iterable<Task> incomingTasks = incoming(tasks, start, end);

        for (Task incomingTask : incomingTasks) {
            LocalDateTime nextTaskDate = incomingTask.getTime();
            Set<Task> taskSet = new HashSet<>();
            for (Task task : incomingTasks) {
                if (task.nextTimeAfter(nextTaskDate).isBefore(end)) {
                    taskSet.add(task);
                }
            }
            calendar.put(nextTaskDate, taskSet);
        }



        return calendar;
    }
}

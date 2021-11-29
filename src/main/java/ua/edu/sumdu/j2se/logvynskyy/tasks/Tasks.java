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
//        ((AbstractTaskList) tasks).getStream()
//                .filter(task -> task.nextTimeAfter(start) != null && task.nextTimeAfter(start).isBefore(end))
//                .forEach(plannedTasks::add);
        for(Task task : tasks) {
            if(task.nextTimeAfter(start) != null && task.nextTimeAfter(start).isBefore(end)) {
                plannedTasks.add(task);
            }
        }
        return plannedTasks;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException {
        if(tasks == null || start == null || end == null) throw new IllegalArgumentException("Список не повинен бути пустим!");
        if(end.isBefore(start)) throw new IllegalArgumentException("Невірні параметри часу!");

        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();

        Iterable<Task> incomingTasks = () -> StreamSupport.stream(tasks.spliterator(), false)
                .distinct()
                .filter(t -> t.nextTimeAfter(start) != null && (t.nextTimeAfter(start).isBefore(end)))
                .iterator();

        for (Task incomingTask : incomingTasks) {
            LocalDateTime nextTaskDate = incomingTask.nextTimeAfter(start);
            Set<Task> taskSet = new HashSet<>();
            for (Task task : incomingTasks) {
                if(task.nextTimeAfter(start).isEqual(nextTaskDate)) {
                    taskSet.add(task);
                }
            }
            calendar.put(nextTaskDate, taskSet);
//            LocalDateTime nextTaskDate = incomingTask.nextTimeAfter(start);
//            while (!nextTaskDate.isAfter(end)) {
//                if ((calendar.containsKey(nextTaskDate)))
//                    calendar.get(nextTaskDate).add(incomingTask);
//                else
//                    calendar.put(nextTaskDate, new HashSet<>(Collections.singleton(incomingTask)));
//                nextTaskDate = incomingTask.nextTimeAfter(nextTaskDate);
//            }
        }
        return calendar;
    }
}

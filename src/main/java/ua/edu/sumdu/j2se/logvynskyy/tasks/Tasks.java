package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.StreamSupport;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException {
        if(tasks == null || start == null || end == null) throw new IllegalArgumentException("Список не повинен бути пустим!");
        if(end.isBefore(start)) throw new IllegalArgumentException("Невірні параметри часу!");

        return () -> StreamSupport.stream(tasks.spliterator(), false)
                .distinct()
                .filter(t -> t.nextTimeAfter(start) != null && (t.nextTimeAfter(start).isBefore(end) ||
                        t.nextTimeAfter(start).isEqual(end)))
                .iterator();
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException {
        if(tasks == null || start == null || end == null) throw new IllegalArgumentException("Список не повинен бути пустим!");
        if(end.isBefore(start)) throw new IllegalArgumentException("Невірні параметри часу!");

        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        Iterable<Task> incomingTasks = incoming(tasks, start, end);

        for (Task incomingTask : incomingTasks) {
            LocalDateTime nextTaskDate = incomingTask.nextTimeAfter(start);
            while (!nextTaskDate.isAfter(end)) {
                if (calendar.containsKey(nextTaskDate))
                    calendar.get(nextTaskDate).add(incomingTask);
                else
                    calendar.put(nextTaskDate, new HashSet<>(Collections.singleton(incomingTask)));
                nextTaskDate = incomingTask.nextTimeAfter(nextTaskDate);
            }
        }
        return calendar;
    }
}

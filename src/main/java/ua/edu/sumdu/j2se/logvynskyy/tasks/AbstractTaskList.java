package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.util.*;
import java.util.stream.*;

public abstract class AbstractTaskList implements Iterable<Task> {

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    public abstract ListTypes.types getInstance();
    public abstract Stream<Task> getStream();

    /**
     * Метод для створення списку задач, які будуть виконані хоч раз у вказаному часовому проміжку.
     * @param from початкова дата відліку
     * @param to кінцева дата відліку
     * @return підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to
     * @throws IllegalArgumentException якщо параметр from менший нуля, або параметр to менший чи дорівнює параметру from
     */
    public final AbstractTaskList incoming(int from, int to) throws IllegalArgumentException{
        if(from < 0 || to <= from) throw new IllegalArgumentException("Невірні параметри часу!");

        AbstractTaskList plannedTasks = TaskListFactory.createTaskList(getInstance());
//        IntStream.range(0, size())
//                .mapToObj(this::getTask)
//                .distinct()
//                .filter(task -> task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to)
//                .forEach(plannedTasks::add);

        getStream().distinct()
                .filter(task -> task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to)
                .forEach(plannedTasks::add);
        return plannedTasks;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Task>{
        int index = 0;
        int lastUsed = -1;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Task next() throws IndexOutOfBoundsException{
            int i = index;
            Task next = getTask(i);
            lastUsed = i;
            index = i + 1;
            return next;
        }

        @Override
        public void remove() {
            if (lastUsed < 0) throw new IllegalStateException();

            AbstractTaskList.this.remove(getTask(lastUsed));
            if (lastUsed < index)
                index--;
            lastUsed = -1;
        }
    }
}
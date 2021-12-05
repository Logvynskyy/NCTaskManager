package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.io.Serializable;
import java.util.*;
import java.util.stream.*;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable {

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    public abstract ListTypes.types getInstance();
    public abstract Stream<Task> getStream();


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
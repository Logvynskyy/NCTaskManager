package ua.edu.sumdu.j2se.logvynskyy.tasks;

abstract class AbstractTaskList {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
}

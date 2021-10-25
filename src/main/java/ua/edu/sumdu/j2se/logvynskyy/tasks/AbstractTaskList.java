package ua.edu.sumdu.j2se.logvynskyy.tasks;

abstract class AbstractTaskList {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    protected AbstractTaskList incoming(int from, int to){
        if(from < 0 && to <= from) throw new IllegalArgumentException("Невірні параметри часу!");
        AbstractTaskList plannedTasks = null;
        for(int i = 0; i < size(); i++){
            Task task = getTask(i);
            if(task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to){
                plannedTasks.add(task);
            }
        }
        return plannedTasks;
    }
}

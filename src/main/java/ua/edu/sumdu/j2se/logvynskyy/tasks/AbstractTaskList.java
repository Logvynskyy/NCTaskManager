package ua.edu.sumdu.j2se.logvynskyy.tasks;

abstract class AbstractTaskList {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    public abstract ListTypes.types getInstance();

    /**
     * Метод для створення списку задач, які будуть виконані хоч раз у вказаному часовому проміжку.
     * @param from початкова дата відліку
     * @param to кінцева дата відліку
     * @return підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to
     * @throws IllegalArgumentException якщо параметр from менший нуля, або параметр to менший чи дорівнює параметру from
     */
    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException{
        if(from < 0 || to <= from) throw new IllegalArgumentException("Невірні параметри часу!");

        AbstractTaskList plannedTasks = TaskListFactory.createTaskList(getInstance());
        for(int i = 0; i < size(); i++){
            Task task = getTask(i);
            if(task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to){
                plannedTasks.add(task);
            }
        }
        return plannedTasks;
    }
}

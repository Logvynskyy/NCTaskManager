package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.util.*;

/**
 * Клас ArrayTaskList, що створює список задач taskList типу Task та має методи, що дозволяють працювати з ним.
 * @author Logvynskyy
 * @version 1.0
 */
public class ArrayTaskList {

    private int size = 10;
    private Task[] taskList = new Task[size];

    /**
     * Метод додає задачу у кінець списку задач
     * @param task - об'єкт класу Task, що потрібно додати до масиву taskList
     */
    public void add(Task task){
        if(isLastEmpty()){
            taskList = Arrays.copyOf(taskList, size + 5);
            size += 5;
        }
        taskList[size()] = task;
    }

    /**
     * Метод видаляє задачу зі списку задач та скорочує його розмір на 1
     * @param task - об'єкт класу Task, що потрібно видалити з масиву taskList
     * @return - true, якщо задача task була у списку, інакше повертає false
     */
    public boolean remove(Task task){
        Task[] result = new Task[taskList.length - 1];
        for(int i = 0; i < taskList.length; i++){
            if(taskList[i].equals(task)){
                System.arraycopy(taskList, 0, result, 0, i);
                System.arraycopy(taskList, i + 1, result, i, taskList.length - i - 1);
                System.arraycopy(result, 0, taskList, 0, result.length);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод повертає кількість задач у списку
     * @return - кількість ненульових об'єктів масиву taskList
     */
    public int size(){
        int counter = 0;
        for (Task task : taskList){
            if (task != null) counter++;
            else break;
        }
        return counter;
    }

    /**
     * Метод повертає задачу, що стоїть на вказаному місці у списку
     * @param index - індекс елемента масиву, який потрібно повернути
     * @return - елемент масиву taskList за вказаним індексом
     */
    public Task getTask(int index){
        return taskList[index];
    }

    private boolean isLastEmpty(){
        return taskList[size - 1] == null;
    }

    /**
     * Метод для створення списку задач, які будуть виконані хоч раз у вказаному часовому проміжку
     * @param from - початкова дата відліку
     * @param to - кінцева дата відліку
     * @return - підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to
     */
    public ArrayTaskList incoming(int from, int to){
        ArrayTaskList plannedTasks = new ArrayTaskList();
        for(int i = 0; i < size(); i++){
            Task task = getTask(i);
            if(task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to){
                plannedTasks.add(task);
            }
        }
        return plannedTasks;
    }
}

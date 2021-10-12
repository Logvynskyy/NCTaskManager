package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.util.*;

public class ArrayTaskList {

    private int size = 10;
    private Task[] taskList = new Task[10];

    public void add(Task task){
        if(!sizeCheck()){
            taskList = Arrays.copyOf(taskList, size + 5);
            size += 5;
            System.out.println(taskList.length);
        }
        taskList[size()] = task;
    }

    public boolean remove(Task task){
        Task[] result = new Task[taskList.length - 1];
        for(int i = 0; i < taskList.length; i++){
            if(taskList[i] == task){
                System.arraycopy(taskList, 0, result, 0, i);
                System.arraycopy(taskList, i + 1, result, i, taskList.length - i - 1);
                System.arraycopy(result, 0, taskList, 0, result.length);
                break;
            }
        }
        return !Arrays.equals(taskList, result);
    }

    public int size(){
        int counter = 0;
        for (Task task : taskList){
            if (task != null) counter++;
            else break;
        }
        return counter;
    }

    public Task getTask(int index){
        return taskList[index];
    }

    private boolean sizeCheck(){
        return taskList[size - 1] != null;
    }
}

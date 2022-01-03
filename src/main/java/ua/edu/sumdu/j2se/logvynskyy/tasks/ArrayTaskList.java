package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Клас ArrayTaskList, що створює список задач taskList типу Task та має методи, що дозволяють працювати з ним.
 * @author Logvynskyy
 * @version 1.0
 */
public class ArrayTaskList extends AbstractTaskList implements Cloneable{

    private int size = 10;
    private Task[] taskList = new Task[size];

    /**
     * Метод додає задачу у кінець списку задач.
     * @param task - об'єкт класу Task, що потрібно додати до масиву taskList
     * @throws NullPointerException якщо задача є пустою та дорівнює null
     */
    @Override
    public void add(Task task) throws NullPointerException{
        if(task == null) throw new NullPointerException("Задача не повинна бути пустою!");
        if(!isLastEmpty()){
            taskList = Arrays.copyOf(taskList, size + 5);
            size += 5;
        }
        taskList[size()] = task;
    }

    /**
     * Метод видаляє задачу зі списку задач та скорочує його розмір на 1.
     * @param t - об'єкт класу Task, що потрібно видалити з масиву taskList
     * @return - true, якщо задача task була у списку, інакше повертає false
     * @throws NullPointerException якщо задача є пустою та дорівнює null
     */
    @Override
    public boolean remove(Task t) throws NullPointerException{
        if(t == null) throw new NullPointerException("Задача не повинна бути пустою!");
        for (int i = 0; i < size; i++) {
            if (taskList[i].equals(t)) {
                if (i + 1 < size) {
                    while (++i < size) {
                        taskList[i - 1] = taskList[i];
                    }
                }
                taskList[--size] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Метод повертає кількість задач у списку.
     * @return - кількість ненульових об'єктів масиву taskList
     */
    @Override
    public int size(){
        int counter = 0;
        for (Task task : taskList){
            if (task != null) counter++;
            else break;
        }
        return counter;
    }

    /**
     * Метод повертає задачу, що стоїть на вказаному місці у списку.
     * @param index - індекс елемента масиву, який потрібно повернути
     * @return - елемент масиву taskList за вказаним індексом
     * @throws IndexOutOfBoundsException якщо index виходить за межі розміру масиву
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if(index >= taskList.length) throw new IndexOutOfBoundsException("Невірно заданий індекс!");
        return taskList[index];
    }

    /**
     * Метод повертає тип колекції для зберігання інформації
     * @return enum-тип, що відноситься до конкретного класу
     */
    @Override
    public ListTypes.types getInstance() {
        return ListTypes.types.ARRAY;
    }

    @Override
    public Stream<Task> getStream() {
        return StreamSupport.stream(Spliterators.spliterator(taskList, 0, size(), 0), false);
    }

    private boolean isLastEmpty(){
        return taskList[size - 1] == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;

        ArrayTaskList that = (ArrayTaskList) o;
        for(int i = 0; i < size(); i++){
            if(!getTask(i).equals(that.getTask(i))) return false;
        }
        return size() == that.size();
    }

    @Override
    public int hashCode() {
        int result = 1;
        for(int i = 0; i < size(); i++){
            result = 31 * result + getTask(i).hashCode();
        }
        return 31 * result + Objects.hashCode(size());
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList cloned = (ArrayTaskList) super.clone();
        cloned.taskList = taskList.clone();
        return cloned;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ArrayTaskList{size = ");
        sb.append(size()).append(", taskList = [");
        for(int i = 0; i < size; i++){
            if(getTask(i) != null)
                sb.append(getTask(i).toString()).append(", ");
        }
        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf(" ") + 1);
        sb.append("]");
        return sb.toString();
    }
}

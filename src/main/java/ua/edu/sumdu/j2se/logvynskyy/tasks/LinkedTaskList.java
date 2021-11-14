package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.util.*;

public class LinkedTaskList extends AbstractTaskList implements Cloneable{
    private Node head;
    private Node last;
    private int size = 0;

    /**
     * Приватний статичний вкладений клас Node, що є вузлом зв'язного списку.
     * Має поля value, що зберігає значення типу Task та next, що зберігає посилання на наступний елемент списку.
     */
    private static class Node {
        public Task value;
        public Node next;

        /**
         * Конструктор вузла зв'язного списку. Посилання на наступний елемент за замовчуванням є null.
         * @param value значення для вузла типу Task
         */
        public Node(Task value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * Метод додає задачу у кінець списку задач.
     * @param task об'єкт класу Task, що потрібно додати до зв'язного списку
     * @throws NullPointerException якщо задача є пустою та дорівнює null
     */
    @Override
    public void add(Task task) throws NullPointerException{
        if(task == null) throw new NullPointerException("Задача не повинна бути пустою!");
        Node node = new Node(task);
        if(isEmpty()){
            head = node;
            last = head;
        }
        else {
            last.next = node;
            last = node;
        }
        size++;
    }

    /**
     * Метод видаляє задачу зі списку задач та скорочує його розмір на 1.
     * @param task об'єкт класу Task, що потрібно видалити зі зв'язного списку
     * @return true, якщо задача task була у списку, інакше повертає false
     * @throws NullPointerException якщо задача є пустою та дорівнює null
     */
    @Override
    public boolean remove(Task task) throws NullPointerException{
        if(task == null) throw new NullPointerException("Задача не повинна бути пустою!");
        Node prev = head;
        Node curr = head;
        while(curr.next != null || curr == last){
            if(curr.value.equals(task)){
                if (size == 1){
                    head = null;
                    last = null;
                }
                else if(curr.equals(head)){ // remove first element
                    head = head.next;
                }
                else if(curr.equals(last)){  // remove last element
                    last = prev;
                    last.next = null;
                }
                else { // remove element
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = prev.next;
        }
        return false;
    }

    /**
     * Метод повертає кількість задач у списку.
     * @return кількість вузлів зв'язного списку
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * Метод повертає задачу, що стоїть на вказаному місці у списку.
     * @param index індекс елемента списку, який потрібно повернути
     * @return елемент зв'язного списку за вказаним індексом
     * @throws IndexOutOfBoundsException якщо index виходить за межі розміру списку
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if(index >= size()) throw new IndexOutOfBoundsException("Невірно заданий індекс!");
        Node temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.value;
    }

    /**
     * Метод повертає тип колекції для зберігання інформації
     * @return enum-тип, що відноситься до конкретного класу
     */
    @Override
    public ListTypes.types getInstance() {
        return ListTypes.types.LINKED;
    }

    private class Iter implements Iterator<Task>{
        int index = 0;
        Node lastUsedNode = new Node(null);

        public Iter() {
            this.lastUsedNode.next = head;
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Task next() throws IndexOutOfBoundsException{
            lastUsedNode = lastUsedNode.next;
            Task next = lastUsedNode.value;
            index++;
            return next;
        }
    }

    private boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        LinkedTaskList tasks = (LinkedTaskList) o;
        for(int i = 0; i < size(); i++){
            if(!getTask(i).equals(tasks.getTask(i))) return false;
        }
        return size == tasks.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, last, size);
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        return (LinkedTaskList) super.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedTaskList{");
        for(int i = 0; i < size(); i++){
            sb.append(getTask(i).toString()).append(", ");
        }
        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf(" ") + 1);
        sb.append("}");
        return sb.toString();
    }
}

package ua.edu.sumdu.j2se.logvynskyy.tasks;

public class LinkedTaskList {
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
    public int size(){
        return size;
    }

    /**
     * Метод повертає задачу, що стоїть на вказаному місці у списку.
     * @param index індекс елемента списку, який потрібно повернути
     * @return елемент зв'язного списку за вказаним індексом
     * @throws IndexOutOfBoundsException якщо index виходить за межі розміру списку
     */
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if(index >= size()) throw new IndexOutOfBoundsException("Невірно заданий індекс!");
        Node temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.value;
    }

    private boolean isEmpty(){
        return size == 0;
    }

    /**
     * Метод для створення списку задач, які будуть виконані хоч раз у вказаному часовому проміжку.
     * @param from початкова дата відліку
     * @param to кінцева дата відліку
     * @return підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to
     * @throws IllegalArgumentException якщо параметр from менший нуля, або параметр to менший чи дорівнює параметру from
     */
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {
        if(from < 0 && to <= from) throw new IllegalArgumentException("Невірні параметри часу!");
        LinkedTaskList plannedTasks = new LinkedTaskList();
        for(int i = 0; i < size(); i++){
            Task task = getTask(i);
            if(task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to){
                plannedTasks.add(task);
            }
        }
        return plannedTasks;
    }
}

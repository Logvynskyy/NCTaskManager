package ua.edu.sumdu.j2se.logvynskyy.tasks;

class Node{
    public Task value; // інформація
    public Node next; // посилання на наступний елемент списку

    public Node(Task value) {
        this.value = value;
    }
}

public class LinkedTaskList {

    Node head;
    Node last;

    private int size = 0;

    public void add(Task task) {
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

    public boolean remove(Task task){
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

    public int size(){
        return size;
    }

    public Task getTask(int index){
        Node temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.value;
    }

    private boolean isEmpty(){
        return size == 0;
    }

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

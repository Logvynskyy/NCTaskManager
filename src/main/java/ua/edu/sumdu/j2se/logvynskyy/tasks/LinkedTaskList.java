package ua.edu.sumdu.j2se.logvynskyy.tasks;

class Node{
    public Task value; // інформація
    public Node next; // посилання на наступний елемент списку

    public Node(Task value) {
        this.value = value;
    }
}

public class LinkedTaskList {

    Node first;
    Node last;

//    public LinkedTaskList() {
//        first = null;
//    }

    public void add(Task task) {
        Node node = new Node(task);

        if (first == null) {
            first = node;
        } else {
            if (last != null) {
                last.next = node;
            } else {
                first = node;
            }
        }
        last = node;
        last.next = null;
    }

    public boolean remove(Task task){
        Node x = first;
        while (x != null){
            Node current = x;
            Node currentNext = current.next;
            x = currentNext;
            if(currentNext != null && currentNext.value.equals(task)){
                current.next = currentNext.next;
                return true;
            }
            if(first.value.equals(task)){
                first = first.next;
                return true;
            }
        }
        return false;
    }

    public int size(){
        Node current = first;
        int count = 0;
        while(current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public Task getTask(int index){
        Node temp = first;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.value;
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

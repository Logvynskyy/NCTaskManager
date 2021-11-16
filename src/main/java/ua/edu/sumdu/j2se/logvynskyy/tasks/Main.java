package ua.edu.sumdu.j2se.logvynskyy.tasks;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello");
		ArrayTaskList al = new ArrayTaskList();
		al.add(new Task("dsad", 10));
		al.add(new Task("dsad", 10));
		al.add(new Task("dsad", 10));
		ArrayTaskList al1 = new ArrayTaskList();
		al1.add(new Task("dsad", 10));
		al1.add(new Task("dsad", 10));
		al1.add(new Task("dsad", 10));
		System.out.println(al);
		System.out.println(al1);
		System.out.println(al.equals(al1));
		al.remove(al.getTask(0));
		al1.remove(al1.getTask(0));
		System.out.println(al.equals(al1));
		al.add(new Task("dsad", 10));
		al1.add(new Task("dsad", 10));
		System.out.println(al.equals(al1));
	}
}

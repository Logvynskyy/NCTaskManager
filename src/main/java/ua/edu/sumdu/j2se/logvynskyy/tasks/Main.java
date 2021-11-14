package ua.edu.sumdu.j2se.logvynskyy.tasks;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello");
		LinkedTaskList ls = new LinkedTaskList();
		ls.add(new Task("dsad", 10));
		ls.add(new Task("dsad", 10));
		ls.add(new Task("dsad", 10));
		System.out.println(ls);
		ArrayTaskList al = new ArrayTaskList();
		al.add(new Task("dsad", 10));
		al.add(new Task("dsad", 10));
		al.add(new Task("dsad", 10));
		System.out.println(al);
	}
}

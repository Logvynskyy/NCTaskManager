package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello");
		AbstractTaskList list = new ArrayTaskList();
		list.add(new Task("A", 10));
		list.add(new Task("B", 20));
		list.add(new Task("C", 30));
		list.add(new Task("D", 10, 40, 10));
		System.out.println(list.incoming(0, 25));
	}
}

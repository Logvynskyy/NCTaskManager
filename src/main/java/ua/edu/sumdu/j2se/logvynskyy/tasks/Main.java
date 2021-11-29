package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello");
		ArrayTaskList list = new ArrayTaskList();
		LocalDateTime now = LocalDateTime.now();

		list.add(new Task("dsad", now));
		list.add(new Task("aaaa", now.plusSeconds(10)));
		list.add(new Task("bbbb", now.plusSeconds(30)));
	}
}

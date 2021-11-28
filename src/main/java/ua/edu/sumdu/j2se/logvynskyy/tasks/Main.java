package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello");
		AbstractTaskList list = new ArrayTaskList();
		LocalDateTime now = LocalDateTime.now();
		Task task = new Task("some", now.plusSeconds(10), now.plusSeconds(100), 20);
		task.setActive(true);
		System.out.println(task.getStartTime());
//		System.out.println(task.getEndTime());
		System.out.println(task.nextTimeAfter(now.plusSeconds(30)));
	}
}

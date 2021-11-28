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
		System.out.println(list.incoming(now, now.plusSeconds(100)));
		System.out.println(Tasks.incoming(list, now, now.plusSeconds(100)));
//		Task task = new Task("some", now.plusSeconds(10), now.plusSeconds(100), 20);
//		task.setActive(true);
//		System.out.println(task.getStartTime());
//		System.out.println(task.getEndTime());
//		System.out.println(task.nextTimeAfter(now.plusSeconds(30)));
//		System.out.println(list instanceof Iterable);
	}
}

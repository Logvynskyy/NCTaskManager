package ua.edu.sumdu.j2se.logvynskyy.tasks;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class Main {

	static LocalDateTime now = LocalDateTime.now();

	public static void main(String[] args) {
		System.out.println("Hello");
		ArrayTaskList list = new ArrayTaskList();
		list.add(createA());
		list.add(createA());
		list.add(createA());

		ArrayTaskList list1 = new ArrayTaskList();

		list1.add(createB());
		list1.add(createB());
		list1.add(createB());

		System.out.println(list.equals(list1));
		System.out.println(list.hashCode() == list1.hashCode());

//		list1.remove(createB());
//		list1.add(createA());
		modify(list);
		modify(list1);
		System.out.println(list);
		System.out.println(list1);

		System.out.println(list.equals(list1));
		System.out.println(list.hashCode() == list1.hashCode());

	}

	private static void modify(AbstractTaskList list){
		IntStream.range(0, list.size()).mapToObj(i -> list.getTask(0)).forEach(list::remove);
		Task A = createA();
		IntStream.range(0, 10).forEach(i -> list.add(A));
	}

	private static Task createA(){
		return new Task("A", now);
	}

	private static Task createB(){
		return new Task("B", now);
	}
}

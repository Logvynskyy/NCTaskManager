package ua.edu.sumdu.j2se.logvynskyy.view;

import ua.edu.sumdu.j2se.logvynskyy.controller.Controller;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Вітаємо вас у додатку Task Manager!");
		System.out.println("Перелік дій, які ви можете виконати:");
		System.out.println("Створити нову задачу - натисніть 1");
		System.out.println("Змінити параметри існуючої задачі - натисніть 2");
		System.out.println("Видалити задачу - натисніть 3");
		System.out.println("Переглянути інформацію про наявні задачі - натисніть 4");
		System.out.println("Переглянути календар запланованих подій - натисніть 5");
		System.out.println("Вивести це повідомлення - натисніть 6");
		System.out.println("Завершити роботу програми - натисніть 7");
		Scanner scanner = new Scanner(System.in);
		Controller controller = Controller.getController();

		while(true){
			System.out.print("Яку задачу будемо виконувати: ");
			try{
				controller.getAction(scanner.nextInt());
			}
			catch (Exception e){
				System.out.println(e.getMessage());
//				System.out.println("Щось пішло не так, спробуйте знову!");
			}
		}

	}
}

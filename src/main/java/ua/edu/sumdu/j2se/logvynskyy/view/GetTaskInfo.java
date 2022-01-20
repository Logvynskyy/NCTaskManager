package ua.edu.sumdu.j2se.logvynskyy.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GetTaskInfo {
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public String getTitle(){
        scanner.useDelimiter("\\n");
        System.out.print("Введіть назву завдання: ");
        return scanner.next();
    }

    public boolean isRepeated(){
        System.out.print("Чи повторюване буде завдання (1 - так, 0 - ні): ");
        return scanner.nextInt() == 1;
    }

    public boolean isActive(){
        System.out.print("Чи активним буде завдання (1 - так, 0 - ні): ");
        return scanner.nextInt() == 1;
    }

    public LocalDateTime getStartTime(){
        scanner.useDelimiter("\\n");
        System.out.print("Введіть час початку виконання завдання у форматі [yyyy-MM-dd HH:mm]: ");
        return LocalDateTime.parse(scanner.next(), formatter);
    }

    public LocalDateTime getEndTime(){
        scanner.useDelimiter("\\n");
        System.out.print("Введіть час кінця виконання завдання у форматі [yyyy-MM-dd HH:mm]: ");
        return LocalDateTime.parse(scanner.next(), formatter);
    }

    public int getInterval(){
        System.out.print("Введіть інтервал виконання завдання у секундах: ");
        return scanner.nextInt();
    }

    public int getID(){
        System.out.print("Введіть номер завдання: ");
        return scanner.nextInt();
    }
}

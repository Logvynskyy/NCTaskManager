package ua.edu.sumdu.j2se.logvynskyy.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GetTaskInfo {
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public String getTitle(){
        System.out.print("Введіть назву задачі: ");
        return scanner.next();
    }

    public boolean isRepeated(){
        System.out.print("Чи повторювана буде задача (1 - так, 0 - ні): ");
        return scanner.nextInt() == 1;
    }

    public boolean isActive(){
        System.out.print("Чи активною буде задача (1 - так, все інше - ні): ");
        return scanner.nextInt() == 1;
    }

    public LocalDateTime getStartTime(){
        System.out.print("Введіть час початку виконання задачі у форматі [yyyy-MM-dd'T'HH:mm]: ");
        return LocalDateTime.parse(scanner.next(), formatter);
    }

    public LocalDateTime getEndTime(){
        System.out.print("Введіть час кінця виконання задачі у форматі [yyyy-MM-dd'T'HH:mm]: ");
        return LocalDateTime.parse(scanner.next(), formatter);
    }

    public int getInterval(){
        System.out.print("Введіть інтервал виконання задачі у секундах: ");
        return scanner.nextInt();
    }
}

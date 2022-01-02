package ua.edu.sumdu.j2se.logvynskyy.view;

public class HelpMessage implements View{
    @Override
    public void getMessage(){
        System.out.println("Перелік дій, які ви можете виконати:");
        System.out.println("Створити нову задачу - натисніть 1");
        System.out.println("Змінити параметри існуючої задачі - натисніть 2");
        System.out.println("Видалити задачу - натисніть 3");
        System.out.println("Переглянути інформацію про наявні завдання - натисніть 4");
        System.out.println("Переглянути календар запланованих подій - натисніть 5");
        System.out.println("Вивести це повідомлення - натисніть 6");
        System.out.println("Завершити роботу програми - натисніть 7");
    }
}

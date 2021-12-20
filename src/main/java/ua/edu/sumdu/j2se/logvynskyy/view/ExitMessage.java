package ua.edu.sumdu.j2se.logvynskyy.view;

public class ExitMessage implements View{
    @Override
    public void getMessage(){
        System.out.println("Дякуємо за використання нашої програми! Гарного дня!");
        System.exit(0);
    }
}

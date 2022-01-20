package ua.edu.sumdu.j2se.logvynskyy.view;

public class FailMessage implements View{
    @Override
    public void getMessage() {
        System.out.println("Виникла помилка. Перевірте введені дані та спробуйте ще раз!");
    }
}

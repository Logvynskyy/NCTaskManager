package ua.edu.sumdu.j2se.logvynskyy.controller;

import ua.edu.sumdu.j2se.logvynskyy.model.Action;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class Controller {
    private static Controller controller = new Controller();

    private Controller() {}

    public static Controller getController() {
        if (controller ==null) {
            controller = new Controller();
        }
        return controller;
    }

    public void getAction(int num) {
        if(num > 7 || num < 1)
            throw new IllegalArgumentException("Введіть коректний номер задачі!");
        Menu menu = Menu.getMenu();
        Action action = menu.getAction(num);
        View page = action.perform();
        page.getMessage();
    }
}

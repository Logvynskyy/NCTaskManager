package ua.edu.sumdu.j2se.logvynskyy.controller;

import ua.edu.sumdu.j2se.logvynskyy.model.*;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private static Menu menu = new Menu();

    private Menu() {}

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    private final Map<Integer, Action> actions = new HashMap<>();
    {
        actions.put(1, new AddTask());
        actions.put(2, new ChangeTask());
        actions.put(3, new DeleteTask());
        actions.put(4, new GetInfo());
        actions.put(5, new GetCalendar());
        actions.put(6, new Help());
        actions.put(7, new Exit());
    }

    public Action getAction(int number) {
        return actions.get(number);
    }
}

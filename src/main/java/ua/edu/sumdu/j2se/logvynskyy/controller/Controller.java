package ua.edu.sumdu.j2se.logvynskyy.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.model.Action;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class);
    private static Controller controller = new Controller();

    private Controller() {}

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        logger.info("The program has started. Controller created");
        Notificator notificator = new Notificator();
        notificator.setDaemon(true);
        notificator.start();
        return controller;
    }

    public void getAction(int num) {
        logger.info("Called an action №" + num);
        if(num > 7 || num < 0){
            logger.error("Called an action that does not exist");
            throw new IllegalArgumentException("Введіть коректний номер задачі!");
        }
        Menu menu = Menu.getMenu();
        Action action = menu.getAction(num);
        View page = action.perform();
        logger.info("Performed an action №" + num + " and returned View " + page.getClass().getSimpleName());
        page.getMessage();
    }
}

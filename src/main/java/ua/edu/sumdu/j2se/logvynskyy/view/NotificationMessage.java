package ua.edu.sumdu.j2se.logvynskyy.view;

public class NotificationMessage implements View{
    private String notifications;

    public NotificationMessage(String notifications) {
        this.notifications = notifications;
    }

    @Override
    public void getMessage() {
        System.out.println(this.notifications);
    }
}

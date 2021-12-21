package ua.edu.sumdu.j2se.logvynskyy.view;

public class CalendarMessage implements View{
    private String calendar;

    public CalendarMessage(String calendar) {
        this.calendar = calendar;
    }

    @Override
    public void getMessage() {
        System.out.println(this.calendar);
    }
}

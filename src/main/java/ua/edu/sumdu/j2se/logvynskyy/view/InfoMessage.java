package ua.edu.sumdu.j2se.logvynskyy.view;

public class InfoMessage implements View{
    private String info;

    public InfoMessage(String info) {
        this.info = info;
    }

    @Override
    public void getMessage() {
        System.out.println(this.info);
    }
}

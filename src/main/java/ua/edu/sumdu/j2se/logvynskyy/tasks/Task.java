package ua.edu.sumdu.j2se.logvynskyy.tasks;

/**
 * Клас Task з описом властивостей задачі
 * @author Logvynskyy
 * @version 1.0
 */

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean isActive;

    /**
     * Конструктор - створення неактивної, неповторюваної задачі
     * @param title - назва задачі
     * @param time - час виконання
     */
    public Task(String title, int time) throws IllegalArgumentException{
        if(time <= 0) throw new IllegalArgumentException("Час не може бути меншим нуля!");
        if(title.isBlank() || title.isEmpty()) throw new IllegalArgumentException("Назва не може бути пустою!");
        this.title = title;
        this.time = time;
    }

    /**
     * Конструктор - створення неактивної, повторюваної задачі
     * @param title - назва задачі
     * @param start - час початку виконання задачі
     * @param end - час кінця виконання задачі
     * @param interval - інтервал повторень виконання задачі
     */
    public Task(String title, int start, int end, int interval) throws IllegalArgumentException{
        if(interval <= 0) throw new IllegalArgumentException("Інтервал менший нуля!");
        if(start <= 0 || end <= start) throw new IllegalArgumentException("Невірні параметри часу!");
        if(title.isBlank() || title.isEmpty()) throw new IllegalArgumentException("Назва не може бути пустою!");
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return - повертає стан активності задачі. За замовчуванням задача неактивна
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Встановлює активність задачі
     * @param active - true означає, що задача стає активною, false - навпаки
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * Метод для роботи з часом у неповторюваних задач
     * @return - повертає час виконання задачі. Якщо задача повторювана - повертає час початку виконання задачі.
     */
    public int getTime() {
        if(isRepeated()) return start;
        else return time;
    }

    /**
     * Змінює час виконання неповторюваної задачі. Якщо задача повторювана, метод перетворює її на неповторювану
     * @param time - новий час виконання задачі.
     */
    public void setTime(int time) {
        if(isRepeated()){
            this.interval = 0;
            this.start = 0;
            this.end = 0;
        }
        this.time = time;
    }

    /**
     * Якщо задача неповторювана, метод перетворює її на повторювану з параметрами
     * @param start - час початку виконання задачі
     * @param end - час кінця виконання задачі
     * @param interval - інтервал виконання задачі
     */
    public void setTime(int start, int end, int interval){
        if(!isRepeated()){
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }

    /**
     * Метод для роботи з повторюваною задачею
     * @return - повертає час початку виконання задачі. Якщо задача неповторювана - повертає загальний час виконання
     */
    public int getStartTime() {
        if(isRepeated()) return start;
        else return time;
    }

    /**
     * Метод для роботи з повторюваною задачею
     * @return - повертає час кінця виконання задачі. Якщо задача неповторювана - повертає загальний час виконання
     */
    public int getEndTime(){
        if(isRepeated()) return end;
        else return time;
    }

    /**
     * Метод для роботи з повторюваною задачею
     * @return - Якщо задача повторювана - повертає інтервал виконання, якщо ні - повертає 0
     */
    public int getRepeatInterval(){
        return interval;
    }

    /**
     * Задача вважається неповторюваною якщо інтервал її виконання дорівнює 0
     * @return - повторювана задача чи ні
     */
    boolean isRepeated(){
        return interval != 0;
    }

    /**
     * Метод працює лише для активних задач та якщо час, що вказано, передує часу закінчення задачі.
     * @param current - вказаний поточний час
     * @return - Якщо виконуються умови, повертається наступний час виконання задачі, інакше повертається -1
     */
    public int nextTimeAfter(int current){
        if(current >= getEndTime() || !isActive()) return -1;
        if(current < getStartTime()) return getStartTime();
        else{
            int tmpTime = getStartTime() + interval;
            while(tmpTime <= current){
                tmpTime += interval;
            }
            if(tmpTime < getEndTime()) return tmpTime;
            else return -1;
        }
    }
}
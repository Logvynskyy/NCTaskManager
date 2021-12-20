package ua.edu.sumdu.j2se.logvynskyy.model.entities;

import java.io.Serializable;
import java.util.*;
import java.time.*;

/**
 * Клас Task з описом властивостей задачі
 * @author Logvynskyy
 * @version 1.0
 */
public class Task implements Cloneable, Serializable {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean isActive;

    /**
     * Конструктор - створення неактивної, неповторюваної задачі
     * @param title - назва задачі
     * @param time - час виконання
     */
    public Task(String title, LocalDateTime time) throws IllegalArgumentException{
        if(time == null || time.isBefore(LocalDateTime.now().minusDays(1))) throw new IllegalArgumentException("Час не може бути меншим нуля!");
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
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException{
        if(interval <= 0) throw new IllegalArgumentException("Інтервал менший нуля!");
        if(start == null || end == null) throw new IllegalArgumentException("Невірні параметри часу!");
        if(end.isBefore(start)) throw new IllegalArgumentException("Час закінчення не може бути раніше часу початку!");
        if(title.isBlank() || title.isEmpty()) throw new IllegalArgumentException("Назва не може бути пустою!");
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException{
        if(title.isBlank() || title.isEmpty()) throw new IllegalArgumentException("Назва не може бути пустою!");
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
    public LocalDateTime getTime() {
        if(isRepeated()) return start;
        else return time;
    }

    /**
     * Змінює час виконання неповторюваної задачі. Якщо задача повторювана, метод перетворює її на неповторювану
     * @param time - новий час виконання задачі.
     */
    public void setTime(LocalDateTime time) throws IllegalArgumentException{
        if(isRepeated()){
            this.interval = 0;
            this.start = null;
            this.end = null;
        }
        this.time = time;
    }

    /**
     * Якщо задача неповторювана, метод перетворює її на повторювану з параметрами
     * @param start - час початку виконання задачі
     * @param end - час кінця виконання задачі
     * @param interval - інтервал виконання задачі
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException{
        if(interval <= 0) throw new IllegalArgumentException("Інтервал менший нуля!");
        if(end.isBefore(start)) throw new IllegalArgumentException("Час закінчення не може бути раніше часу початку!");
        if(!isRepeated()){
            this.time = null;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Метод для роботи з повторюваною задачею
     * @return - повертає час початку виконання задачі. Якщо задача неповторювана - повертає загальний час виконання
     */
    public LocalDateTime getStartTime() {
        if(isRepeated()) return start;
        else return time;
    }

    /**
     * Метод для роботи з повторюваною задачею
     * @return - повертає час кінця виконання задачі. Якщо задача неповторювана - повертає загальний час виконання
     */
    public LocalDateTime getEndTime(){
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
    public boolean isRepeated(){
        return interval != 0;
    }

    /**
     * Метод працює лише для активних задач та якщо час, що вказано, передує часу закінчення задачі.
     * @param current - вказаний поточний час
     * @return - Якщо виконуються умови, повертається наступний час виконання задачі, інакше повертається -1
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) throws IllegalArgumentException {
        if(!isActive() || current.compareTo(getEndTime()) >= 0) return null;
        if(current.isBefore(getStartTime())) return getStartTime();
        else{
            LocalDateTime tmpTime = getStartTime().plusSeconds(interval);
            while (!tmpTime.isAfter(current)) {
                tmpTime = tmpTime.plusSeconds(interval);
            }
            if (!tmpTime.isAfter(getEndTime())) return tmpTime;
            else return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        if(!isRepeated())
            return time.equals(task.time) && title.equals(task.title) && isActive == task.isActive;
        else
            return start.equals(task.start) && end.equals(task.end) && interval == task.interval && isActive == task.isActive && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, isActive);
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    @Override
    public String toString() {
        if(!isRepeated())
            return "Task{" +
                    "title = '" + title + '\'' +
                    ", isRepeated = " + isRepeated() +
                    ", time = " + time.toString() +
                    '}';
        else
            return "Task{" +
                    "title='" + title + '\'' +
                    ", isRepeated = " + isRepeated() +
                    ", start = " + start.toString() +
                    ", end = " + end.toString() +
                    ", interval = " + interval +
                    ", isActive = " + isActive +
                    '}';
    }
}
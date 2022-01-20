package ua.edu.sumdu.j2se.logvynskyy.model.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.AbstractTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out){
        try(ObjectOutputStream oos = new ObjectOutputStream(out)){
            oos.writeInt(tasks.size());
            for (Task task : tasks) {
                oos.writeInt(task.getTitle().length());
                oos.writeChars(task.getTitle());
                oos.writeBoolean(task.isActive());
                oos.writeInt(task.getRepeatInterval());
                oos.writeObject(task.getStartTime());
                if (task.isRepeated())
                    oos.writeObject(task.getEndTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in){
        try(ObjectInputStream ois = new ObjectInputStream(in)){
            int size = ois.readInt();
            for(int i = 0; i < size; i++){
                int titleLength = ois.readInt();
                char[] title = new char[titleLength];
                for(int j = 0; j < titleLength; j++)
                    title[j] = ois.readChar();
                boolean isActive = ois.readBoolean();
                int interval = ois.readInt();
                LocalDateTime startTime = (LocalDateTime) ois.readObject();
                Task task;
                if(interval == 0){
                    task = new Task(String.valueOf(title), startTime);
                }
                else{
                    task = new Task(String.valueOf(title), startTime, (LocalDateTime) ois.readObject(), interval);
                }
                task.setActive(isActive);
                tasks.add(task);
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            write(tasks, oos);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            read(tasks, ois);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try {
            out.write(gson.toJson(tasks.getStream().toArray(Task[]::new)));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in){
        Gson gson = new Gson();

        Task[] taskArray = gson.fromJson(in, Task[].class);
        Arrays.stream(taskArray).forEach(tasks::add);
    }

    public static void writeText(AbstractTaskList tasks, File file){
        try(FileWriter fw = new FileWriter(file)) {
            write(tasks, fw);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file){
        try(FileReader fr = new FileReader(file)) {
            read(tasks, fr);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

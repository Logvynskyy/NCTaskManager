package ua.edu.sumdu.j2se.logvynskyy.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        AbstractTaskList classType = null;
        switch(type){
            case ARRAY:
                classType = new ArrayTaskList();
                break;
            case LINKED:
                classType = new LinkedTaskList();
                break;
        }
        return classType;
    }
}

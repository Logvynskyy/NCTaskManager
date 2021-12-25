package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.Tasks;
import ua.edu.sumdu.j2se.logvynskyy.view.CalendarMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GetCalendar implements Action{
    @Override
    public View perform() {
//        Map<LocalDateTime, Set<Task>> calendar =  Tasks.calendar(ArrayTaskList.getList(),
//                (LocalDateTime) DataFactory.getData(DataType.START_TIME),
//                (LocalDateTime) DataFactory.getData(DataType.END_TIME));

        Map<LocalDateTime, Set<Task>> calendar =  Tasks.calendar(ArrayTaskList.getList(),
                LocalDateTime.now().minusDays(20),
                LocalDateTime.now());

        StringBuilder calendarAsString = new StringBuilder("{");
        for (LocalDateTime key : calendar.keySet()) {
            calendarAsString.append("У дату - ").append(key.toString()).append(" відбулися події\r\n");
            Tasks[] tasks = (Tasks[]) calendar.values().toArray();
            for (Tasks task : tasks)
                calendarAsString.append(task).append(", ");
        }
//        calendarAsString.delete(calendarAsString.lastIndexOf(","), calendarAsString.lastIndexOf(" ") + 1);

        return new CalendarMessage(calendarAsString.toString());
    }
}

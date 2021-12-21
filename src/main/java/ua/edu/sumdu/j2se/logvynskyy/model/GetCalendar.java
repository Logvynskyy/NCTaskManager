package ua.edu.sumdu.j2se.logvynskyy.model;

import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.Tasks;
import ua.edu.sumdu.j2se.logvynskyy.view.CalendarMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GetCalendar implements Action{
    @Override
    public View perform() {
        Map<LocalDateTime, Set<Task>> calendar =  Tasks.calendar(ArrayTaskList.getList(),
                (LocalDateTime) DataFactory.getData(DataType.START_TIME),
                (LocalDateTime) DataFactory.getData(DataType.END_TIME));

        StringBuilder calendarAsString = new StringBuilder("{");
        for (LocalDateTime key : calendar.keySet()) {
            calendarAsString.append("У дату - ").append(key.toString()).append(" відбулися події\r\n");
            for(Set<Task> tasks : calendar.values())
                calendarAsString.append(tasks.iterator().next().toString()).append(", ");
        }
        calendarAsString.delete(calendarAsString.length()-2, calendarAsString.length()).append("}");

//        String calendarAsString = calendar.keySet().stream()
//                .map(key -> "У дату - " + key.toString() + " відбулися події " + calendar.get(key))
//                .collect(Collectors.joining(", ", "{", "}"));
        return new CalendarMessage(calendarAsString.toString());
    }
}

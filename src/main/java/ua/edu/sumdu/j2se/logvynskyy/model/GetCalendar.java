package ua.edu.sumdu.j2se.logvynskyy.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.logvynskyy.controller.DataFactory;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.ArrayTaskList;
import ua.edu.sumdu.j2se.logvynskyy.model.entities.Task;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.DataType;
import ua.edu.sumdu.j2se.logvynskyy.model.utils.Tasks;
import ua.edu.sumdu.j2se.logvynskyy.view.CalendarMessage;
import ua.edu.sumdu.j2se.logvynskyy.view.View;

import java.time.LocalDateTime;
import java.util.*;

public class GetCalendar implements Action{
    private static final Logger logger = Logger.getLogger(GetCalendar.class);

    @Override
    public View perform() {
        Map<LocalDateTime, Set<Task>> calendar =  Tasks.calendar(ArrayTaskList.getList(),
                (LocalDateTime) DataFactory.getData(DataType.START_TIME),
                (LocalDateTime) DataFactory.getData(DataType.END_TIME));
        StringBuilder calendarAsString = new StringBuilder();

        if(calendar.size() == 0)
            return new CalendarMessage("У цей час немає жодної події");

        for (LocalDateTime date : calendar.keySet()) {
            calendarAsString.append("\nУ дату - ").append(date.toLocalDate()).append(" з часом ")
                    .append(date.toLocalTime()).append(" відбулася подія");
            Set<Task> tasks = calendar.get(date);
            for (Task task : tasks) {
                calendarAsString.append(" ").append(task.getTitle()).append(",");
            }
            calendarAsString.deleteCharAt(calendarAsString.lastIndexOf(","));
        }

        logger.info("Formed a calendar of tasks in two given dates");
        return new CalendarMessage(calendarAsString.toString());
    }
}

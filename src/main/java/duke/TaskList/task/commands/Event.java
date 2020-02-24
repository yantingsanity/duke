package duke.TaskList.task.commands;

import duke.TaskList.task.Task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h1>Event</h1>
 * The Event class contains the descriptions and functions
 * of a Event task.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

public class Event extends Task {
    private char taskType;
    private LocalDateTime eventDateTime;
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter dateTimeFormat2 = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String description, String date) {
        super(description);
        taskType = 'E';
        getDateTime(date);
    }

    /**
     * Converts the String dateTime into a LocalDateTime object.
     *
     * @param date the date and time in String
     * @return Nothing
     * @throws DateTimeParseException if the date and time are not in the correct format
     */

    public void getDateTime(String date) throws DateTimeParseException {
        String [] dateTimeSplit = date.split(" ");
        if (dateTimeSplit.length > 1){
            date = dateTimeSplit[0] + "T" + dateTimeSplit[1];
            eventDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        } else {
            LocalDate eventDate = LocalDate.parse(date);
            eventDateTime = eventDate.atStartOfDay();
        }
    }

    /**
     * Prints out the details of the Event task.
     *
     * @return a string containing all the details of Event task.
     */

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription() +
                " (at: " + getDateTimeStringFormat2() + ")";
    }

    /**
     * Gets the task type which is "E" for this task.
     *
     * @return E since it is Event task.
     */

    @Override
    public char getTaskType() {
        return this.taskType;
    }

    /**
     * Gets the date for this Event task.
     *
     * @return date for the task.
     */

    @Override
    public LocalDateTime getDateTime() {
        return eventDateTime;
    }

    /**
     * Gets the date for this Event task in terms of String format (yyyy-MM-dd HH:mm)
     *
     * @return date for the task.
     */

    @Override
    public String getDateTimeString(){
        return eventDateTime.format(dateTimeFormat);
    }

    /**
     * Gets the date for this Event task in terms of String format (MMM d yyyy)
     *
     * @return date for the task.
     */

    @Override
    public String getDateTimeStringFormat2(){
        return eventDateTime.format(dateTimeFormat2);
    }

}

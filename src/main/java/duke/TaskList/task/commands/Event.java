package duke.TaskList.task.commands;

import duke.TaskList.task.Task;
import duke.UI.UI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * <h1>Event</h1>
 * The Event class contains the descriptions and functions
 * of a Event task.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-03-01
 */

public class Event extends Task {
    private char taskType;
    private LocalDateTime eventDateTime;
    private LocalDate eventDateOnly;
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dateTimeFormat2 = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("MMM d yyyy");


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
     * @throws  DateTimeParseException if the date and time are not in the correct format
     */

    public void getDateTime(String date) throws DateTimeParseException{
        String [] dateTimeSplit = date.split(" ");
        if (dateTimeSplit.length > 1) {
            date = dateTimeSplit[0] + "T" + dateTimeSplit[1];
            eventDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        } else {
            eventDateOnly = LocalDate.parse(date);
        }
    }

    /**
     * Prints out the details of the Event task.
     *
     * @return a string containing all the details of Event task.
     */

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription() +
                " (at: " + getDateTimeStringForDuke() + ")";
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
     * Gets the date for this Event task in terms of String format (yyyy-MM-dd HH:mm)
     * This is used to save into our output file.
     *
     * @return date for the task.
     */

    @Override
    public String getDateTimeStringForFile(){
        if (eventDateTime == null){
            return eventDateOnly.format(dateFormat);
        } else {
            return eventDateTime.format(dateTimeFormat);
        }
    }

    /**
     * Gets the date for this Event task in terms of String format (MMM d yyyy)
     *
     * @return date for the task.
     */

    @Override
    public String getDateTimeStringForDuke(){
        if (eventDateTime == null){
            return eventDateOnly.format(dateFormat2);
        } else {
            return eventDateTime.format(dateTimeFormat2);
        }
    }

}

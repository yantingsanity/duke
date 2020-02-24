package duke.TaskList.task.commands;

import duke.TaskList.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h1>Deadline</h1>
 * The Deadline class contains the descriptions and functions
 * of a Deadline task.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

public class Deadline extends Task {
    private char taskType;
    private LocalDateTime deadlineDateTime;
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    DateTimeFormatter dateTimeFormat2 = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, String date) {
        super(description);
        taskType = 'D';
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
            deadlineDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        } else {
            LocalDate eventDate = LocalDate.parse(date);
            deadlineDateTime = eventDate.atStartOfDay();
        }
    }

    /**
     * Prints out the details of the Deadline task.
     *
     * @return a string containing all the details of Deadline task.
     */

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription() +
                " (by: " + getDateTimeStringFormat2() + ")";
    }

    /**
     * Gets the task type which is "D" for this task.
     *
     * @return D since it is Deadline task.
     */

    @Override
    public char getTaskType() {
        return this.taskType;
    }

    /**
     * Gets the date for this Deadline task.
     *
     * @return date for the task.
     */
    @Override
    public LocalDateTime getDateTime(){
        return deadlineDateTime;
    }

    /**
     * Gets the date for this Deadline task in terms of String format (yyyy-MM-dd HH:mm)
     *
     * @return date for the task.
     */

    @Override
    public String getDateTimeString(){
        return deadlineDateTime.format(dateTimeFormat);
    }

    /**
     * Gets the date for this Deadline task in terms of String format (MMM d yyyy)
     *
     * @return date for the task.
     */

    @Override
    public String getDateTimeStringFormat2(){
        return deadlineDateTime.format(dateTimeFormat2);
    }
}

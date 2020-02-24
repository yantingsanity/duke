package duke.TaskList.task.commands;

import duke.TaskList.task.Task;
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

    public void getDateTime(String date) throws DateTimeParseException {
        String [] dateTimeSplit = date.split(" ");
        if (dateTimeSplit.length > 1){
            date = dateTimeSplit[0] + "T" + dateTimeSplit[1];
            deadlineDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        } else {
            deadlineDateTime = LocalDateTime.parse(date, dateFormat);
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

    @Override
    public String getDateTimeString(){
        return deadlineDateTime.format(dateTimeFormat);
    }

    @Override
    public String getDateTimeStringFormat2(){
        return deadlineDateTime.format(dateTimeFormat2);
    }
}

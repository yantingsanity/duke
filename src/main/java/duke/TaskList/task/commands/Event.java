package duke.TaskList.task.commands;

import duke.TaskList.task.Task;

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
    private String eventDate;

    public Event(String description, String date) {
        super(description);
        taskType = 'E';
        eventDate = date;
    }

    /**
     * Prints out the details of the Event task.
     *
     * @return a string containing all the details of Event task.
     */

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription() +
                " (at: " + eventDate + ")";
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
    public String getDate() {
        return this.eventDate;
    }
}

package duke.TaskList.task.commands;

import duke.TaskList.task.Task;

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
    private String deadlineDate;
    public Deadline(String description, String date) {
        super(description);
        taskType = 'D';
        deadlineDate = date;
    }

    /**
     * Prints out the details of the Deadline task.
     *
     * @return a string containing all the details of Deadline task.
     */

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription() +
                " (by: " + deadlineDate + ")";
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
    public String getDate(){
        return this.deadlineDate;
    }
}

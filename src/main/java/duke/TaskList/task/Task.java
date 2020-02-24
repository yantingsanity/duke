package duke.TaskList.task;

/**
 * <h1>Task</h1>
 * The Task class contains the descriptions
 * of a single task.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

import java.time.LocalDateTime;

public abstract class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Set the isDone variable as done, indicating that the
     * task is done.
     *
     * @return Nothing
     */

    public void setTaskAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the status of the task in terms of ticks and crosses.
     *
     * @return ✓ if task is done, else ✗
     */

    public String getTaskStatus() {
        if (isDone == true){
            return "✓";
        } else {
            return "✗";
        }
    }

    /**
     * Gets the description of the task.
     *
     * @return task description
     */

    public String getTaskDescription() {
        return description;
    }

    /**
     * Gets the type of the task (deadline, event, todo).
     *
     * @return the task type (T, E or D)
     */

    public char getTaskType() {
        return 0;
    }

    /**
     * Gets the date for the task if there is any.
     *
     * @return date for the task
     */

    public LocalDateTime getDateTime() {
        return null;
    }

    /**
     * Gets the date for this task in terms of String format (yyyy-MM-dd HH:mm)
     *
     * @return date for the task.
     */

    public String getDateTimeString() {
        return "";
    };

    /**
     * Gets the date for this task in terms of String format (MMM d yyyy)
     *
     * @return date for the task.
     */
    public String getDateTimeStringFormat2() {
        return "";
    };
}

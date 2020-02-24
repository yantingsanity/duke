package duke.TaskList.task.commands;

import duke.TaskList.task.Task;

/**
 * <h1>ToDo</h1>
 * The ToDo class contains the descriptions and functions
 * of a Todo task.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

public class ToDo extends Task {
    private char taskType;

    public ToDo(String description) {
        super(description);
        taskType = 'T';
    }

    /**
     * Prints out the details of the ToDo task.
     *
     * @return a string containing all the details of ToDo task.
     */

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription();
    }

    /**
     * Gets the task type which is "T" for this task.
     *
     * @return T since it is ToDo task.
     */

    @Override
    public char getTaskType() {
        return this.taskType;
    }
}

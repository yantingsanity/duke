package duke.TaskList.task.commands;

import duke.TaskList.task.Task;

public class ToDo extends Task {
    private char taskType;

    public ToDo(String description) {
        super(description);
        taskType = 'T';
    }

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription();
    }

    @Override
    public char getTaskType() {
        return this.taskType;
    }
}

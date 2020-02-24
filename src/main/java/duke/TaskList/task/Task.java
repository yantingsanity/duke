package duke.TaskList.task;

import java.time.LocalDateTime;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    //set the duke.TaskList.task.task.Task as done
    public void setTaskAsDone() {
        this.isDone = true;
    }

    public String getTaskStatus() {
        if (isDone == true){
            return "✓";
        } else {
            return "✗";
        }
    }

    public String getTaskDescription() {
        return description;
    }

    public char getTaskType() {
        return 0;
    }

    public LocalDateTime getDateTime() {
        return null;
    }

    public String getDateTimeString() {
        return "";
    };

    public String getDateTimeStringFormat2() {
        return "";
    };
}

package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    //set the duke.task.Task as done
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

    public String getDate(){
        return "";
    }
}

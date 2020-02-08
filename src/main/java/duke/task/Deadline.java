package duke.task;

public class Deadline extends Task {
    private char taskType;
    private String deadlineDate;
    public Deadline(String description, String date) {
        super(description);
        taskType = 'D';
        deadlineDate = date;
    }

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription() +
                " (by: " + deadlineDate + ")";
    }
}

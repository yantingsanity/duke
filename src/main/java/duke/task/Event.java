package duke.task;

public class Event extends Task{
    private char taskType;
    private String eventDate;

    public Event(String description, String date) {
        super(description);
        taskType = 'E';
        eventDate = date;
    }

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription() +
                " (at: " + eventDate + ")";
    }
}

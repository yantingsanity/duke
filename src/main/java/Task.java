public class Task {
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
    }

    //set the Task as done
    public void setTaskAsDone(){
        this.isDone = true;
    }

    public boolean getTaskStatus(){
        return isDone;
    }

    public String getTaskDescription(){
        return description;
    }
}

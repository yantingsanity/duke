package duke.TaskList;

import duke.Storage.Storage;
import duke.TaskList.task.Task;
import duke.exceptions.InvalidListSizeException;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> totalTasks;

    /**
     * Initialise the array list for tasks
     */
    public TaskList(){
        totalTasks = new ArrayList<>();
    }

    /**
     * Prints out all the task contents in the list
     */
    public void printTaskList() throws InvalidListSizeException, NullPointerException {
        if (totalTasks.size() > 0){
            System.out.println("Here are the tasks in your list:");
            int index = 0;
            for (Task task : totalTasks){
                System.out.println((++index) + ". " + task);
            }
        } else {
            throw new InvalidListSizeException();
        }
    }

    public void addNewTask(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        totalTasks.add(newTask);

    }

    public void printTotalSize(){
        System.out.println("Now you have " + totalTasks.size() + " tasks in the list.");
    }

    public int getTaskID(String userCommand){
        int index = 0;
        try {
            String [] inputStrings = userCommand.split(" ");
            if (inputStrings.length > 1){
                index = Integer.parseInt(inputStrings[1].trim()) - 1;
            }
            return index;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Please provide an appropriate index to update/delete!");
            return -1;
        }
    }

    public void setTaskDone(String userCommand) throws IndexOutOfBoundsException {
        int taskNum = getTaskID(userCommand);
        if (taskNum != -1){
            Task task = totalTasks.get(taskNum);
            task.setTaskAsDone();
            System.out.println("Nice! I have marked this task as done: \n" + task);
        }
    }

    public void deleteTask(String userCommand) throws IndexOutOfBoundsException {
        int taskNum = getTaskID(userCommand);
        if (taskNum != -1){
            Task task = totalTasks.get(taskNum);
            task.setTaskAsDone();
            System.out.println(task);
            totalTasks.remove(taskNum);
            printTotalSize();
        }
    }
}

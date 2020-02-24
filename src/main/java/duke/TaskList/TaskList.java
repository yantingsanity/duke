package duke.TaskList;

import duke.TaskList.task.Task;
import duke.exceptions.InvalidListSizeException;

import java.util.ArrayList;

/**
 * <h1>TaskList</h1>
 * The TaskList class deals with the different functions
 * related to the Task such as adding, editing and deleting.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

public class TaskList {

    public static ArrayList<Task> totalTasks;

    /**
     * Initialise an ArrayList for tasks
     *
     * @return Nothing
     */

    public TaskList(){
        totalTasks = new ArrayList<>();
    }

    /**
     * Prints out all the tasks in the current list
     *
     * @return Nothing
     * @throws InvalidListSizeException  If totalTasks.size() = 0.
     * @throws NullPointerException If there is no instance of totalTasks
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

    /**
     * Adds a new task to the current list of tasks.
     *
     * @param newTask the new task to be added
     * @return Nothing
     */

    public void addNewTask(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        totalTasks.add(newTask);
    }

    /**
     * Prints the size of the current list of tasks.
     *
     * @return Nothing
     */

    public void printTotalSize(){
        System.out.println("Now you have " + totalTasks.size() + " tasks in the list.");
    }

    /**
     * Gets the index of the task that the user specified
     *
     * @param userCommand the command that the user has inputted
     * @return returns index which is an ID of the task specified, else -1 if there are no tasks in list
     */

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

    /**
     * Sets a task as done with a tick.
     *
     * @param userCommand the command that the user has inputted
     * @return Nothing
     * @throws IndexOutOfBoundsException if the index that the user specified is not in the current list
     */

    public void setTaskDone(String userCommand) throws IndexOutOfBoundsException {
        int taskNum = getTaskID(userCommand);
        if (taskNum != -1){
            Task task = totalTasks.get(taskNum);
            task.setTaskAsDone();
            System.out.println("Nice! I have marked this task as done: \n" + task);
        }
    }

    /**
     * Deletes a task.
     *
     * @param userCommand the command that the user has inputted
     * @return Nothing
     * @throws IndexOutOfBoundsException if the index that the user specified is not in the current list
     */

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

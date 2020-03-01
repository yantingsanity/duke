package duke.TaskList;

import duke.TaskList.task.Task;
import duke.UI.UI;

import java.util.ArrayList;

/**
 * <h1>TaskList</h1>
 * The TaskList class deals with the different functions
 * related to the Task such as adding, editing and deleting.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-03-01
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
     */

    public void printTaskList() {
        try {
            if (totalTasks.size() > 0) {
                System.out.println("Here are the tasks in your list:");
                int index = 0;
                for (Task task : totalTasks){
                    System.out.println((++index) + ". " + task);
                }
            }
        } catch (NullPointerException e) {
            UI.getErrorMessage("zeroTasks");
        }
    }

    /**
     * Adds a new task to the current list of tasks.
     * If addType = "load", do not print any statements.
     *
     * @param newTask the new task to be added
     * @return Nothing
     */

    public void addNewTask(Task newTask, String addType) {
        if (!addType.equals("load")) {
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
        }
        totalTasks.add(newTask);
    }

    /**
     * Prints the size of the current list of tasks.
     *
     * @return Nothing
     */

    public void printTotalSize() {
        System.out.println("Now you have " + totalTasks.size() + " tasks in the list.");
    }

    /**
     * Gets the index of the task that the user specified
     *
     * @param userCommand the command that the user has inputted
     * @return returns index which is an ID of the task specified, else -1 if there are no tasks in list
     */

    public int getTaskID(String userCommand) {
        String [] inputStrings = userCommand.split(" ");
        if (inputStrings.length > 1) {
            int index = Integer.parseInt(inputStrings[1].trim()) - 1;
            return index;
        }
        return -1;
    }

    /**
     * Sets a task as done with a tick.
     *
     * @param userCommand the command that the user has inputted
     * @return Nothing
     */

    public void setTaskDone(String userCommand) {
        int taskNum = getTaskID(userCommand);
        try {
            Task task = totalTasks.get(taskNum);
            task.setTaskAsDone();
            System.out.println("Nice! I have marked this task as done: \n" + task);
        } catch (IndexOutOfBoundsException e) {
            UI.getErrorMessage("updateIndex");
        }
    }

    /**
     * Deletes a task.
     *
     * @param userCommand the command that the user has inputted
     * @return Nothing
     */

    public void deleteTask(String userCommand) {
        int taskNum = getTaskID(userCommand);
        try {
            Task task = totalTasks.get(taskNum);
            task.setTaskAsDone();
            System.out.println(task + " has been deleted!");
            totalTasks.remove(taskNum);
            printTotalSize();
        } catch (IndexOutOfBoundsException e) {
            UI.getErrorMessage("deleteIndex");
        }
    }

    /**
     * Finds a specific task with the given keyword supplied by the user.
     *
     * @param findString the keyword that user supplied
     * @return Nothing
     */

    public void findTasks(String findString) {
        int index = 0;
        for (Task newTask : totalTasks) {
            if (newTask.getTaskDescription().contains(findString)) {
                System.out.println((++index) + ". " + newTask);
            }
        }
        if (index == 0) {
            System.out.println("There are no tasks with that keyword! :(");
        }
    }
}

package duke.Parser;

import duke.TaskList.TaskList;
import duke.TaskList.task.Task;
import duke.TaskList.task.commands.Deadline;
import duke.TaskList.task.commands.Event;
import duke.TaskList.task.commands.ToDo;
import duke.UI.UI;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidListSizeException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import static duke.Duke.storage;

/**
 * <h1>Parser</h1>
 * This class retrieves the user command from the I/O and parses
 * it in a way where the application will be able to make sense
 * of the command and execute them.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

public class Parser {

    String userCommand = "";
    String [] taskStrings;
    String [] taskDescriptions;

    private static TaskList totalTasks;

    public Parser(TaskList newTasks){
        totalTasks = newTasks;
    }

    /**
     * Returns true if user has inputted "bye" to terminate the program.
     * Else returns false if user has not inputted "bye".
     *
     * @return true if user inputted bye, else false
     */

    public boolean isBye (){
        if (this.userCommand.equals("bye")){
            return true;
        }
        return false;
    }

    /**
     * Read in the user input and split them into different strings.
     *
     * @param userInput
     * @return Nothing
     */

    public void readUserInput(String userInput){
        this.taskStrings = userInput.split("/");
        this.userCommand = userInput;
    }

    /**
     * Execute the command based on the user input.
     *
     * @return Nothing
     * @throws InvalidInputException  If userCommand is not any valid command.
     * @throws InvalidListSizeException If there is currently no tasks in the TaskList
     * @throws IndexOutOfBoundsException If there are less/more arguments for the commands
     * @throws IOException If the duke.txt cannot be opened or written into
     */

    public void executeCommand() throws InvalidInputException, InvalidListSizeException,
                                                IndexOutOfBoundsException, IOException {
        if (isBye() == false){
            if (this.userCommand.equals("list")){
                listCommand(this.totalTasks);
            } else {
                this.taskDescriptions = this.taskStrings[0].split(" ", 2);
                if (this.userCommand.contains("done")){
                    this.totalTasks.setTaskDone(this.userCommand);
                } else if (this.userCommand.contains("delete")){
                    this.totalTasks.deleteTask(this.userCommand);
                } else if (this.userCommand.contains("find")) {
                    this.totalTasks.findTasks(taskDescriptions[1]);
                } else {
                    taskCommand(this.totalTasks);
                }
                storage.writeToFile(this.totalTasks);
            }
        }
    }

    /**
     * Return the date of the task in String format.
     *
     * @return the date of the task in String format
     * @throws IndexOutOfBoundsException  If taskStrings only has one value after the split
     */

    public String getTaskDate() throws IndexOutOfBoundsException{
        return this.taskStrings[1].split(" ", 2)[1];
    }

    /**
     * Creates a new task of the following types (todo, event, deadline)
     * based on the user command.
     *
     * @param totalTasks the current list of tasks
     * @throws InvalidInputException  If commands are invalid
     */
    public void taskCommand(TaskList totalTasks) throws InvalidInputException, DateTimeParseException {
        Task newTask;
        try {
            switch (this.taskDescriptions[0].trim()) {
            case "todo":
                newTask = new ToDo(taskDescriptions[1]);
                break;
            case "event":
                newTask = new Event(taskDescriptions[1], getTaskDate());
                break;
            case "deadline":
                newTask = new Deadline(taskDescriptions[1], getTaskDate());
                break;
            default:
                throw new InvalidInputException(this.userCommand);
            }
            totalTasks.addNewTask(newTask);
            totalTasks.printTotalSize();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is a missing parameter in your input!");
            UI.getHelpMessage();
        }
    }

    /**
     * Prints out all the tasks that are currently in the list.
     *
     * @param totalTasks the current list of tasks
     * @throws InvalidListSizeException  If totalTasks.size() is 0
     */

    public void listCommand(TaskList totalTasks) throws InvalidListSizeException {
        try {
            totalTasks.printTaskList();
        } catch (NullPointerException e){
            System.out.println("There is currently nothing in your list!");
        }
    }
}

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

public class Parser {

    String userCommand = "";
    String [] taskStrings;
    String [] taskDescriptions;

    private static TaskList totalTasks;

    public Parser(TaskList newTasks){
        totalTasks = newTasks;
    }

    public boolean isBye (){
        if (this.userCommand.equals("bye")){
            return true;
        }
        return false;
    }

    public void readUserInput(String userInput){
        this.taskStrings = userInput.split("/");
        this.userCommand = userInput;
    }

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
                } else {
                    taskCommand(this.totalTasks);
                }
                System.out.println(totalTasks);
                storage.writeToFile(this.totalTasks);
            }
        }
    }

    public String getTaskDate() throws IndexOutOfBoundsException{
        return this.taskStrings[1].split(" ", 2)[1];
    }

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

    public void listCommand(TaskList totalTasks) throws InvalidListSizeException {
        try {
            totalTasks.printTaskList();
        } catch (NullPointerException e){
            System.out.println("There is currently nothing in your list!");
        }
    }
}

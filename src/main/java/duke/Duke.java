package duke;

import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.UI.UI;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidListSizeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * <h1>Duke</h1>
 * The Duke program allows users to manage their tasks by
 * adding, editing and deleting their tasks from the task list.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

public class Duke {

    public static Storage storage;
    private static TaskList totalTasks;
    private static UI ui;
    private static Parser newParser;

    /**
     * Initialise the Duke application with new Storage and UI.
     * Loading lists from a current file
     *
     * @return Nothing
     */

    public static void initialise() {
        ui = new UI();
        storage = new Storage("\\duke.txt");
        try {
            totalTasks = storage.loadFile();
        } catch (FileNotFoundException e){
            ui.getErrorMessage("fileIO");
            totalTasks = new TaskList();
        }
    }

    /**
     * Starts the program. Program will terminate once isBye == true.
     *
     * @return Nothing
     */

    public static void run() {
        boolean isBye = false;
        newParser = new Parser(totalTasks);
        while (!isBye){
            String userInput = ui.getUserInput();
            newParser.readUserInput(userInput);
            try {
                newParser.executeCommand();
                isBye = newParser.isBye();
            } catch (InvalidInputException e){
                ui.getErrorMessage("invalidInput");
            } catch (InvalidListSizeException e){
                ui.getErrorMessage("zeroTasks");
            } catch (IndexOutOfBoundsException e){
                ui.getErrorMessage("indexOutOfBounds");
            } catch (IOException e){
                ui.getErrorMessage("fileIOModified");
            } catch (DateTimeParseException e){
                ui.getErrorMessage("dateTime");
            }
            System.out.println("____________________________________________________________________");
        }
    }

    /**
     * Close the Duke application with a "bye".
     *
     * @return Nothing
     */

    public static void end() {
        ui.closeInput();
    }

    public static void main(String [] args) {
        initialise();
        run();
        end();
    }
}

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

public class Duke {

    public static Storage storage;
    private static TaskList totalTasks;
    private static UI ui;

    public static void initialise() {
        ui = new UI();
        storage = new Storage("\\data\\duke.txt");
        try {
            totalTasks = storage.loadFile();
        } catch (FileNotFoundException e){
            ui.getErrorMessage("fileIO");
            totalTasks = new TaskList();
        }
    }

    public static void run() {
        boolean isBye = false;
        Parser newParser = new Parser(totalTasks);
        while (!isBye){
            String userInput = UI.getUserInput();
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

    public static void end(){
        UI.closeInput();
    }

    public static void main(String [] args){
        initialise();
        run();
        end();
    }
}

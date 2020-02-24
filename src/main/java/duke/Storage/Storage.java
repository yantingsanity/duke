package duke.Storage;

import duke.TaskList.TaskList;
import duke.TaskList.task.Task;
import duke.TaskList.task.commands.Deadline;
import duke.TaskList.task.commands.Event;
import duke.TaskList.task.commands.ToDo;
import duke.UI.UI;
import duke.exceptions.InvalidListSizeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * <h1>Storage</h1>
 * The Storage class deals with the input and output functions
 * of the "duke.txt" file.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

public class Storage {
    public boolean doesFileExist;
    String pathToFile = "";
    String homePath = System.getProperty("user.dir");

    public Storage(String filePath){
        this.pathToFile = filePath;
        this.doesFileExist = checkIfFileExists();
    }

    /**
     * Creates a new "duke.txt" file if it cannot be found.
     *
     * @return Nothing
     */

    public void createFile() {
        String filepath = this.homePath + "\\duke.txt";
        File newFile = new File(filepath);
        try {
            newFile.createNewFile();
        } catch (IOException e){
            UI.getErrorMessage("fileCreation");
        }
        this.pathToFile = filepath;
    }

    /**
     * Returns true if the file exists.
     * Else, return false.
     *
     * @return true if file exists, else false.
     */

    public boolean checkIfFileExists() {
        String filepath = this.homePath + this.pathToFile;
        System.out.println(filepath);
        this.doesFileExist = new java.io.File(filepath).exists();
        this.pathToFile = filepath;
        return this.doesFileExist;
    }

    /**
     * Returns the list of tasks loaded from "duke.txt".
     *
     * @return list of tasks loaded from file
     * @throws FileNotFoundException  If file cannot be found.
     */

    public TaskList loadFile() throws FileNotFoundException, DateTimeParseException {

        TaskList newTasks = new TaskList();

        File f = new File(this.pathToFile);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String newLine = s.nextLine();
            String[] contents = newLine.split(",", 4);
            Task newTask = null;
            switch (contents[0]) {
            case "T":
                newTask = new ToDo(contents[2]);
                break;
            case "E":
                newTask = new Event(contents[2], contents[3]);
                break;
            case "D":
                newTask = new Deadline(contents[2], contents[3]);
                break;
            }
            if (contents[1].equals("1")) {
                newTask.setTaskAsDone();
            }
            newTasks.addNewTask(newTask);
        }
        return newTasks;
    }


    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param listToSave current list of tasks to save into file
     * @return Nothing
     * @throws IOException if file canno be opened or written to.
     */
  
    public void writeToFile(TaskList listToSave) throws IOException, InvalidListSizeException {

        if (!this.doesFileExist){
            createFile();
        }
        FileWriter fw = new FileWriter(this.pathToFile);
        String stringToWrite = "";
        listToSave.printTaskList();
        for (Task task : listToSave.totalTasks){
            System.out.println(task);
            String status = "";
            if (task.getTaskStatus() == "✓"){
                status = "1";
            } else {
                status = "0";
            }
            System.out.println(task.getTaskType());
            switch (task.getTaskType()){
            case 'T':
                stringToWrite = task.getTaskType() + "," + status + "," + task.getTaskDescription() + "\n";
                break;
            case 'E':
            case 'D':
                stringToWrite = task.getTaskType() + "," + status + "," + task.getTaskDescription() + "," +
                                    task.getDateTimeString() + "\n";
                break;
            }
            fw.write(stringToWrite);
        }
        fw.close();
    }
}

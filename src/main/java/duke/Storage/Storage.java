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

public class Storage {
    public boolean doesFileExist;
    String pathToFile = "";
    String homePath = System.getProperty("user.dir");

    public Storage(String filePath){
        this.pathToFile = filePath;
        this.doesFileExist = checkIfFileExists();
    }

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

    public boolean checkIfFileExists() {
        String filepath = this.homePath + this.pathToFile;
        System.out.println(filepath);
        this.doesFileExist = new java.io.File(filepath).exists();
        this.pathToFile = filepath;
        return this.doesFileExist;
    }

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

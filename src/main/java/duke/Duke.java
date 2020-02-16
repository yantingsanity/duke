package duke;

import duke.exceptions.InvalidInputException;
import duke.task.Task;
import duke.task.commands.Deadline;
import duke.task.commands.Event;
import duke.task.commands.ToDo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static ArrayList <Task> totalTasks = new ArrayList<>();

    public static void getWelcomeMessage(){
        String logo = "  __  .__             /\\            .___     __           \n" +
                "_/  |_|__| ____    ___)/ ______   __| _/_ __|  | __ ____  \n" +
                "\\   __\\  |/    \\  / ___\\/  ___/  / __ |  |  \\  |/ // __ \\ \n" +
                " |  | |  |   |  \\/ /_/  >___ \\  / /_/ |  |  /    <\\  ___/ \n" +
                " |__| |__|___|  /\\___  /____  > \\____ |____/|__|_ \\\\___  >\n" +
                "              \\//_____/     \\/       \\/          \\/    \\/ ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you, today?");
        System.out.println("____________________________________________________________________");
    }

    public static void getHelpMessage() {
        System.out.println("Some help for you!! :)");
        System.out.println("3 different ways to add tasks into the list:");
        System.out.println("+ event <event_name> /at <event date> <event time>");
        System.out.println("+ todo <todo_task>");
        System.out.println("+ deadline <deadline_task> /by <deadline date> <deadline time>");
    }

    public static void printAllTasks(){
        if (totalTasks.size() > 0){
            System.out.println("Here are the tasks in your list:");
            int index = 0;
            for (Task task : totalTasks){
                System.out.println((++index) + ". " + task);
            }
        } else {
            System.out.println("There are currently no tasks in your list!");
        }
    }

    public static void updateTaskStatus(String userInput){
        String [] inputStrings = userInput.split(" ");
        String updateType = inputStrings[0];
        if (inputStrings.length > 1) {
            int index = Integer.parseInt(inputStrings[1].trim()) - 1;
            Task task = null;
            try {
                task = totalTasks.get(index);
                if (updateType.equals("done")) {
                    task.setTaskAsDone();
                    System.out.println("Nice! I have marked this task as done:");
                    System.out.println(task);
                } else if (updateType.equals("delete")) {
                    System.out.println("Noted. I've removed this task:");
                    task.setTaskAsDone();
                    System.out.println(task);
                    totalTasks.remove(index);
                    System.out.println("Now you have " + totalTasks.size() + " in the list.");
                } else {
                    getHelpMessage();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please provide an appropriate index to update/delete!");
            }
        } else {
            System.out.println("Please provide an index to update/delete!");
        }
    }

    public static void addNewTask(String userInput) throws InvalidInputException{
        String [] taskStrings = userInput.split("/");
        String [] taskDescriptions = taskStrings[0].split(" ", 2);
        String [] taskDate = new String[0];
        if (taskStrings.length > 1){
            taskDate = taskStrings[1].split(" ", 2);
        }
        try {
            Task newTask;
            switch (taskDescriptions[0].trim()){
            case "todo":
                newTask = new ToDo(taskDescriptions[1]);
                break;
            case "event":
                newTask = new Event(taskDescriptions[1], taskDate[1]);
                break;
            case "deadline":
                newTask = new Deadline(taskDescriptions[1], taskDate[1]);
                break;
            default:
                throw new InvalidInputException();
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            //Add new task
            totalTasks.add(newTask);
            System.out.println("Now you have " + totalTasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e){
            System.out.println("There is a missing parameter in your input!");
            getHelpMessage();
        }
    }

    public static void writeToFile() throws IOException {
        //String filepath = "../../../src/main/java/duke/data/duke.txt";
        String filepath = "src/main/java/duke/data/duke.txt";
        FileWriter fw = new FileWriter(filepath);
        String stringToWrite = "";
        for (Task task : totalTasks){
            String status = "";
            if (task.getTaskStatus() == "✓"){
                status = "1";
            } else {
                status = "0";
            }

            switch (task.getTaskType()){
            case 'T':
                stringToWrite = task.getTaskType() + "," + status + "," + task.getTaskDescription() + "\n";
                break;
            case 'E':
            case 'D':
                stringToWrite = task.getTaskType() + "," + status + "," + task.getTaskDescription() + "," + task.getDate() + "\n";
                break;
            }
            fw.write(stringToWrite);
        }
        fw.close();
    }

    public static void loadFileToTotalTasks() {
        try {
            //File f = new File("../../../src/main/java/duke/data/duke.txt");
            File f = new File("src/main/java/duke/data/duke.txt");
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
                totalTasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void main(String[] args){

        getWelcomeMessage();
        loadFileToTotalTasks();

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")){
                printAllTasks();
            } else if (userInput.contains("done") || userInput.contains("delete")){
                updateTaskStatus(userInput);
                try {
                    writeToFile();
                } catch (IOException e){
                    System.out.println(e);
                }
            } else {
                try {
                    addNewTask(userInput);
                    try {
                        writeToFile();
                    } catch (IOException e){
                        System.out.println(e);
                    }
                } catch (InvalidInputException e){
                    System.out.println("OOPS ONO, I'm so sorry, but I don't know what this (" + userInput + ") means");
                    System.out.println("Please try again!");
                }
            }
            System.out.println("____________________________________________________________________");
            userInput = input.nextLine();
        }

        System.out.println("BYE BYE SEE YOU SOON!");
        input.close();
    }
}

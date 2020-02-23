package duke.UI;

import java.util.Scanner;

public class UI {

    private static Scanner input = new Scanner(System.in);

    public UI(){
        getWelcomeMessage();
    }

    public static void getWelcomeMessage() {
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
        String helpMessage = "Here are some help for you!! :) \n" +
                "1) list: List out all the tasks in your list \n" +
                "2) Creation of tasks into list:\n" +
                "   a) todo <todo_task>\n" +
                "   b) deadline <deadline_task> /by <deadline date> <deadline time>\n" +
                "   c) event <event_name> /at <event date> <event time>\n" +
                "3) done <index>: To indicate that the task is done\n" +
                "4) delete <index>: To delete a task\n" +
                "5) bye: To end the program";
        System.out.println(helpMessage);
    }

    public static String getUserInput() {
        String userInput = input.nextLine();
        return userInput;
    }

    public static void closeInput() {
        System.out.println("BYE BYE SEE YOU SOON!");
        input.close();
    }

    public static void getErrorMessage(String type) {
        switch (type){
        case "fileIO":
            //System.out.println("File cannot be found! Created a new file instead.");
            break;
        case "invalidInput":
            System.out.println("You have entered an invalid command!");
            getHelpMessage();
            break;
        case "zeroTasks":
            System.out.println("There is currently no tasks in the list!");
            break;
        case "indexOutOfBounds":
            System.out.println("Please choose a correct index!");
            break;
        case "fileCreation":
            System.out.println("File cannot be created!");
            break;
        case "fileIOModified":
            System.out.println("Error with the file!");
        default:
            getHelpMessage();
        }
    }
}

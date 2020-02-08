import java.util.Scanner;

public class Duke {

    public static void welcomeMessage(){
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

    public static Task createNewTask(String userInput) throws InvalidInputException{
        String [] task = userInput.split("/");
        String [] taskDescription = task[0].split(" ", 2);
        String [] taskDate = new String[0];
        if (task.length > 1) {
            taskDate = task[1].split(" ", 2);
        }
        Task newTask = null;
        try {
            switch (taskDescription[0].trim()){
            case "todo":
                newTask = new ToDo(taskDescription[1]);
                break;
            case "event":
                newTask = new Event(taskDescription[1], taskDate[1]);
                break;
            case "deadline":
                newTask = new Deadline(taskDescription[1], taskDate[1]);
                break;
            default:
                throw new InvalidInputException();
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            return newTask;

        } catch (IndexOutOfBoundsException e){
            getHelpMessage();
            return null;
        }
    }

    public static void getHelpMessage() {
        System.out.println("There is a missing parameter in your input!");
        System.out.println("Some help for you!! :)");
        System.out.println("3 different ways to add tasks into the list:");
        System.out.println("+ event <event_name> /at <event date> <event time>");
        System.out.println("+ todo <todo_task>");
        System.out.println("+ deadline <deadline_task> /by <deadline date> <deadline time>");
    }

    public static void printAllTasks(Task [] totalTasks, int index){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i += 1){
            System.out.println((i + 1) + ". " + totalTasks[i]);
        }
    }

    public static void updateTaskStatus(Task taskToCheck){
        taskToCheck.setTaskAsDone();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(taskToCheck);
    }

    public static void main(String[] args) {
        Task [] totalTasks = new Task[100];
        int index = 0;

        welcomeMessage();

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        try {
            while (!userInput.equals("bye")){
                if (userInput.equals("list")){
                    printAllTasks(totalTasks, index);
                } else if (userInput.contains("done")){
                    String [] splitInput = userInput.split(" ");
                    updateTaskStatus(totalTasks[Integer.parseInt(splitInput[1].trim()) - 1]);
                } else {
                    totalTasks[index] = createNewTask(userInput);
                    if (totalTasks[index] != null) {
                        index += 1;
                        System.out.println("Now you have " + index + " tasks in the list.");
                    }
                }
                System.out.println("____________________________________________________________________");
                userInput = input.nextLine();
            }
        } catch (InvalidInputException e){
            System.out.println("OOPS ONO, I'm so sorry, but I don't know what this (" + userInput + ") means");
        }

        System.out.println("BYE BYE SEE YOU BACK SOON!");
        input.close();
    }
}

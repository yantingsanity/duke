import java.util.Scanner;

public class Duke {

    public static void updateTaskStatus(Task taskToCheck){
        taskToCheck.setTaskAsDone();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println("[✓] " + taskToCheck.getTaskDescription());
    }

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

    public static void main(String[] args) {
        //String [] totalTasks = new String[100];
        Task [] totalTasks = new Task[100];
        int index = 0;

        welcomeMessage();

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")){
            if (userInput.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i += 1){
                    System.out.print((i + 1) + ". ");
                    if (totalTasks[i].getTaskStatus()){
                        System.out.print("[✓] ");
                    } else {
                        System.out.print("[✗] ");
                    }
                    System.out.println(totalTasks[i].getTaskDescription());
                }
            } else if (userInput.contains("done")){
                String [] splitInput = userInput.split(" ");
                updateTaskStatus(totalTasks[Integer.parseInt(splitInput[1].trim()) - 1]);
            } else {
                Task newTask = new Task(userInput);
                System.out.println("added: " + newTask.getTaskDescription());
                totalTasks[index] = newTask;
                index += 1;
            }
            System.out.println("____________________________________________________________________");
            userInput = input.nextLine();
        }
        System.out.println("BYE BYE SEE YOU BACK SOON!");
        input.close();
    }
}

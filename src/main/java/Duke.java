import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String [] totalTasks = new String[100];
        int index = 0;
        String logo = "  __  .__             /\\            .___     __           \n" +
                "_/  |_|__| ____    ___)/ ______   __| _/_ __|  | __ ____  \n" +
                "\\   __\\  |/    \\  / ___\\/  ___/  / __ |  |  \\  |/ // __ \\ \n" +
                " |  | |  |   |  \\/ /_/  >___ \\  / /_/ |  |  /    <\\  ___/ \n" +
                " |__| |__|___|  /\\___  /____  > \\____ |____/|__|_ \\\\___  >\n" +
                "              \\//_____/     \\/       \\/          \\/    \\/ ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you, today?");
        System.out.println("____________________________________________________________________");

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")){
            if (userInput.equals("list")){
                for (int i = 0; i < index; i += 1){
                    System.out.println((i+1) + ". " + totalTasks[i]);
                }
            } else {
                totalTasks[index] = userInput;
                System.out.println("added: " + totalTasks[index]);
                index += 1;
            }
            System.out.println("____________________________________________________________________");
            userInput = input.nextLine();
        }
        System.out.println("BYE BYE SEE YOU BACK SOON!");
        input.close();
    }
}

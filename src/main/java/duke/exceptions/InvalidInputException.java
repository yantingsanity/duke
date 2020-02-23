package duke.exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message){
        System.out.println("OOPS ONO, I'm so sorry, but I don't know what this (" + message + ") means");
    }
}

package duke.exceptions;

/**
 * <h1>InvalidInputException</h1>
 * The class deals with the exception when users entered an incorrect command.
 *
 * @author  Lim Yan Ting
 * @version 2.0
 * @since   2020-02-24
 */

public class InvalidInputException extends Exception {
    public InvalidInputException(String message){
        System.out.println("OOPS ONO, I'm so sorry, but I don't know what this (" + message + ") means");
    }
}

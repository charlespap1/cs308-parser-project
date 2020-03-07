package slogo.model.exceptions;

public class InvalidCommandException extends RuntimeException {

    private static final String errorMessage = "ERROR: Invalid Command Inputted";

    public InvalidCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

}

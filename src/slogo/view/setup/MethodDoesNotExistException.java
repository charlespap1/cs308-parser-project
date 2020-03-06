package slogo.view.setup;

public class MethodDoesNotExistException extends RuntimeException {
    private static final String errorMessage = "ERROR: Invalid Display Command.";

    public MethodDoesNotExistException()
    {
        super();
    }

    @Override
    public String getMessage()
    {
        return errorMessage;
    }

}

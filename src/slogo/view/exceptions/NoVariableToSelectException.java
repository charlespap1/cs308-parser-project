package slogo.view.exceptions;

public class NoVariableToSelectException extends RuntimeException {

    private static final String errorMessage = "ERROR: Cannot Alter Null Variable";

    public NoVariableToSelectException(Exception e) {
        super(e);
    }

    /**
     * Gets the unique error message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return errorMessage;
    }

}

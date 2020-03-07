package slogo.model.exceptions;

public class SyntaxException extends RuntimeException {

    private static final String errorMessage = "ERROR: Syntax, check your spacing!";

    public SyntaxException(Exception e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

}

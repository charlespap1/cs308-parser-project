package slogo.model.exceptions;

public class InvalidNumberArgumentsException extends RuntimeException {

  private static final String errorMessage = "ERROR: Invalid Number of Arguments Inputted";

  public InvalidNumberArgumentsException()
  {
    super();
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

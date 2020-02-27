package slogo.model.code.exceptions;

public class InvalidCommandException extends RuntimeException {

  private String errorMessage = "ERROR: Invalid Command Inputted";

  public InvalidCommandException()
  {
    super();
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

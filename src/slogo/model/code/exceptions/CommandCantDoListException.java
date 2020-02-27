package slogo.model.code.exceptions;

public class CommandCantDoListException extends RuntimeException {

  private static final String errorMessage = "ERROR: Command Does Not Take a List";

  public CommandCantDoListException()
  {
    super();
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

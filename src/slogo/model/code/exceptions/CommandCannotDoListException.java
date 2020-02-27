package slogo.model.code.exceptions;

public class CommandCannotDoListException extends RuntimeException {

  private static final String errorMessage = "ERROR: Command Does Not Take a List";

  public CommandCannotDoListException()
  {
    super();
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

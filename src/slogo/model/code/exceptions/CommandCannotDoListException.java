package slogo.model.code.exceptions;

public class CommandCannotDoListException extends RuntimeException {

  private String errorMessage = "ERROR: Command Does Not Take a List";

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

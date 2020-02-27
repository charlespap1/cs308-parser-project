package slogo.model.code.exceptions;

public class ListNotIntegerException extends RuntimeException {

  private String errorMessage = "ERROR: Command Does Not Take a List";

  public ListNotIntegerException()
  {
    super();
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

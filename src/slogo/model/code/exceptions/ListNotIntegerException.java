package slogo.model.code.exceptions;

public class ListNotIntegerException extends RuntimeException {

  private String errorMessage = "ERROR: Command Does Not Take a List";

  public ListNotIntegerException(Exception e)
  {
    super(e);
    System.out.println(errorMessage);
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

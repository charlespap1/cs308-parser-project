package slogo.model.code.exceptions;

public class ListNotIntegerException extends RuntimeException {

  private String errorMessage;

  public ListNotIntegerException(Exception e, String errorMessage)
  {
    super(e);
    this.errorMessage = errorMessage;
    System.out.println(errorMessage);
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

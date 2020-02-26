package slogo.model.code.exceptions;

public class InvalidNumberArgumentsException extends RuntimeException {

  private String errorMessage;

  public InvalidNumberArgumentsException(Exception e, String errorMessage)
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

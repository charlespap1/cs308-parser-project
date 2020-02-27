package slogo.model.code.exceptions;

public class InvalidNumberArgumentsException extends RuntimeException {

  private String errorMessage = "ERROR: Invalid Number of Arguments Inputted";

  public InvalidNumberArgumentsException()
  {
    super();
    System.out.println(errorMessage);
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

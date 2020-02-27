package slogo.model.code.exceptions;

public class InvalidArgumentException extends RuntimeException {

  private static final String errorMessage = "ERROR: Invalid Argument Inputted";

  public InvalidArgumentException()
  {
    super();
  }

  @Override
  public String getMessage() { return errorMessage; }

}

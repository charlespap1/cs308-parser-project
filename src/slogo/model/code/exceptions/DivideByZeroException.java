package slogo.model.code.exceptions;

public class DivideByZeroException extends RuntimeException {

  private static final String errorMessage = "ERROR: Cannot Divide Nonzero Number By Zero";

  public DivideByZeroException()
  {
    super();
  }

  @Override
  public String getMessage() { return errorMessage; }

}

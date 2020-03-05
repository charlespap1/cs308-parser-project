package slogo.model.exceptions;

public class InvalidLoopConditionException extends RuntimeException{

  private static final String errorMessage = "ERROR: Invalid Loop Condition";

  public InvalidLoopConditionException()
  {
    super();
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

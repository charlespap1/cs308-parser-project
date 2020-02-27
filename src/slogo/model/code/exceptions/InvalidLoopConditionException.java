package slogo.model.code.exceptions;

public class InvalidLoopConditionException extends RuntimeException{

  private String errorMessage = "ERROR: Invalid Loop Condition";

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

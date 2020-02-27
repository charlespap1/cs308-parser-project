package slogo.model.code.exceptions;

public class InvalidLoopCondtionException extends RuntimeException{

  private String errorMessage = "ERROR: Invalid Loop Condition";

  public InvalidLoopCondtionException()
  {
    super();
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

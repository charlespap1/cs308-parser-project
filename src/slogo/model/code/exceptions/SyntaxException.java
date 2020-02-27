package slogo.model.code.exceptions;

public class SyntaxException extends RuntimeException{

  private String errorMessage = "ERROR: Syntax, check your spacing!";

  public SyntaxException(Exception e)
  {
    super(e);
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

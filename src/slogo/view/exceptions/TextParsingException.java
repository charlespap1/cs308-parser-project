package slogo.view.exceptions;

public class TextParsingException extends RuntimeException{

  private static final String errorMessage = "ERROR: Parser Could Not Write Given Input";

  public TextParsingException(Exception e)
  {
    super(e);
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }
}

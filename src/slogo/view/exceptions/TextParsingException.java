package slogo.view.exceptions;

/**
 * Exception for a file.io exception when writing to a file
 * @author Juliet
 */
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

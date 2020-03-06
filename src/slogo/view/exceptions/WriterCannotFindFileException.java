package slogo.view.exceptions;

/**
 * Exception for when the writer cannot find the file created
 * Should never happen because it is making a new package from whatever you
 * just inputted
 */
public class WriterCannotFindFileException extends RuntimeException{

  private static final String errorMessage = "ERROR: Writer Cannot Find Inputted File";

  public WriterCannotFindFileException(Exception e)
  {
    super(e);
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }
}




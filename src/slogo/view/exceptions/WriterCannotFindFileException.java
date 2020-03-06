package slogo.view.exceptions;

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




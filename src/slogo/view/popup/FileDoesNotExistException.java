package slogo.view.popup;

public class FileDoesNotExistException extends RuntimeException{

  private static final String errorMessage = "ERROR: File Does Not Exist";

  public FileDoesNotExistException(Exception e)
  {
    super(e);
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }
}


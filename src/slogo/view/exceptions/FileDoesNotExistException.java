package slogo.view.exceptions;

/**
 * Exception for when someone tries to load a file but the
 * filename they input does not exist
 */
public class FileDoesNotExistException extends RuntimeException{

  private static final String errorMessage = "ERROR: File Does Not Exist, check your spelling!";

  public FileDoesNotExistException(Exception e)
  {
    super(e);
  }

  /**
   * Gets the unique error message
   * @return
   */
  @Override
  public String getMessage()
  {
    return errorMessage;
  }
}


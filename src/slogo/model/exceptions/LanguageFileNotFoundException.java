package slogo.model.exceptions;

public class LanguageFileNotFoundException extends RuntimeException{

  private static final String errorMessage = "ERROR: Language File Not Found";

  public LanguageFileNotFoundException(Exception e)
  {
    super(e);
  }

  @Override
  public String getMessage()
  {
    return errorMessage;
  }

}

package slogo.model.code.exceptions;

public class LanguageFileNotFoundException extends RuntimeException{

  private String errorMessage = "ERROR: Language File Not Found";

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

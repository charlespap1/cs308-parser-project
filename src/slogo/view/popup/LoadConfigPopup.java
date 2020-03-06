package slogo.view.popup;

import java.io.File;
import java.util.Scanner;
import javafx.scene.control.TextField;
import slogo.view.exceptions.FileDoesNotExistException;

public class LoadConfigPopup extends PopupSkeleton {

  public static final int TEXT_FIELD_WIDTH = 125;
  public static final String PACKAGE = "resources/%s.txt";

  private TextField myInput;

  public LoadConfigPopup ()
  {
    super();
    myInput = new TextField();
    myInput.setMaxWidth(TEXT_FIELD_WIDTH);
    myHolder.getChildren().add(2, myInput);
  }

  public String getFilePackage()
  {
    return String.format(PACKAGE, myInput.getText());
  }



  public File getFile() throws FileDoesNotExistException
  {
    String path = String.format(PACKAGE, myInput.getText());
    try {
      File f = new File(path);
      Scanner myReader = new Scanner(f);
      return f;

    } catch (Exception e) {
        throw new FileDoesNotExistException(e);
    }
  }


}

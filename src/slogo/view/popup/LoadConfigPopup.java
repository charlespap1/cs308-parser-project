package slogo.view.popup;

import javafx.scene.control.TextField;
import slogo.view.exceptions.FileDoesNotExistException;

import java.io.File;
import java.util.Scanner;

/**
 * Popup for when you want the program to load or save a text file.
 * Allows for input of filenames which are converted into package statement
 * @author Juliet
 */
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

  /**
   * Used to create a path for the new file to exist, will be located in resources folder and
   * names as a text file of whatever input the user gives
   * @return
   */
  public String getFilePackage()
  {
    return String.format(PACKAGE, myInput.getText());
  }

  /**
   * Used to grab a file from resources. If file not found, throws FileDoesNotExist exception
   * to print an error
   * @return
   * @throws FileDoesNotExistException
   */
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

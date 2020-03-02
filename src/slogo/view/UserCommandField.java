package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

/**
 * This class allows us to specify the attributes of the user input box
 * @author Juliet
 */
public class UserCommandField {

  public static final double FIELD_SIDE_PADDING = DrawingCanvas.CANVAS_SIDE_PADDING;
  public static final double FIELD_BOTTOM_PADDING = DrawingCanvas.CANVAS_BOTTOM_PADDING;

  private TextArea myTextField;

  public UserCommandField(double screenWidth, double screenHeight)
  {
    myTextField = new TextArea();
    myTextField.setMaxWidth(screenWidth/3 - 2*FIELD_SIDE_PADDING);
    myTextField.setMinHeight(screenHeight/2 - FIELD_BOTTOM_PADDING);
    myTextField.setLayoutX(screenWidth/3 + FIELD_SIDE_PADDING);
    myTextField.setLayoutY(screenHeight/2);
  }

  /**
   * This method allows us to get the text from the input box
   * to be passed on to the back end of our project
   * @return
   */
  public String getUserInput()
  {
    return myTextField.getText();
  }

  /**
   * Allows us to add the text input box to the main root
   * @return
   */
  public Node getView()
  {
    return myTextField;
  }

  /**
   * Allow us to place the go button below them
   * @return
   */
  public double getWidth()
  {
    return myTextField.getMaxWidth();
  }

  public void setUserInput(String command) { myTextField.setText(command); }
}

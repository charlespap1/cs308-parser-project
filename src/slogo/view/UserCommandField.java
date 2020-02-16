package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

/**
 * This class allows us to specify the attributes of the user input box
 */
public class UserCommandField {

  public static final double FIELD_SIDE_PADDING = DrawingCanvas.CANVAS_SIDE_PADDING;
  public static final double FIELD_TOP_PADDING = DrawingCanvas.CANVAS_TOP_PADDING;
  public static final double FIELD_BOTTOM_PADDING = DrawingCanvas.CANVAS_BOTTOM_PADDING;
  private TextArea myTextField;


  public UserCommandField(double screenWidth, double screenHeight)
  {

    myTextField = new TextArea();
    myTextField.setMaxWidth(screenWidth/2 - 2*FIELD_SIDE_PADDING);
    myTextField.setMinHeight(screenHeight - FIELD_TOP_PADDING - FIELD_BOTTOM_PADDING);
    myTextField.setLayoutX(screenWidth/2 + FIELD_SIDE_PADDING);
    myTextField.setLayoutY(FIELD_TOP_PADDING);

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


}

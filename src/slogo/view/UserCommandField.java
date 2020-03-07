package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

import java.util.List;

/**
 * This class allows us to specify the attributes of the user input box
 *
 * @author Juliet
 */
public class UserCommandField implements StaticViewElement {

    private static final double FIELD_SIDE_PADDING = DrawingCanvas.CANVAS_SIDE_PADDING;
    private static final double FIELD_BOTTOM_PADDING = DrawingCanvas.CANVAS_BOTTOM_PADDING;

    private TextArea myTextField;

    public UserCommandField(double screenWidth, double screenHeight) {
        myTextField = new TextArea();
        myTextField.setMaxWidth(screenWidth / 3 - 2 * FIELD_SIDE_PADDING);
        myTextField.setMinHeight(screenHeight / 2 - FIELD_BOTTOM_PADDING);
        myTextField.setLayoutX(screenWidth / 3 + FIELD_SIDE_PADDING);
        myTextField.setLayoutY(screenHeight / 2);
    }

    /**
     * This method allows us to get the text from the input box
     * to be passed on to the back end of our project
     *
     * @return
     */
    public String getUserInput() {
        String instructions = myTextField.getText();
        myTextField.clear();
        return instructions;
    }

    /**
     * Allows us to add the text input box to the main root
     *
     * @return
     */
    @Override
    public Node getView() {
        return myTextField;
    }

    /**
     * User Command field doesn't have associated text
     *
     * @param sp
     */
    @Override
    public void setTitleProperty(List<StringProperty> sp) {

    }


    /**
     * Allow us to place the go button below them
     *
     * @return
     */
    public double getWidth() {
        return myTextField.getMaxWidth();
    }

    public void setUserInput(String command) {
        myTextField.setText(command);
    }
}

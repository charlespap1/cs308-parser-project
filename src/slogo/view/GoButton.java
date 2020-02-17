package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class GoButton implements ViewButton {

  public static final String BUTTON_TEXT = "Go";

  private UserCommandField myUserInput;
  private Button myButton;

  public GoButton(UserCommandField userInput)
  {
    myUserInput = userInput;
    myButton = new Button(BUTTON_TEXT);
    myButton.setMinWidth(myUserInput.getWidth());
    myButton.setOnAction(e -> execute());
  }

  @Override
  public void execute() {
    System.out.println(myUserInput.getUserInput());
  }

  public Node getView()
  {
    return myButton;
  }

  public double getHeight()
  {
    return myButton.getHeight();
  }
}

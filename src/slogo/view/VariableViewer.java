package slogo.view;

import javafx.scene.text.Text;

public class VariableViewer extends ScrollingWindow{
  public static final String VARIABLE_VIEWER_TITLE = "Your Variables: ";

  public VariableViewer(double elementWidthFactor, double topPadding) {
    super(elementWidthFactor, topPadding, VARIABLE_VIEWER_TITLE);
  }

  public void addVariable(String variableName, int value)
  {
    Text text = new Text(String.format("  %s   has value   %d", variableName, value));
    myTextHolder.getChildren().add(text);

  }

}

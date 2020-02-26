package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

public class TurtleCommandPanel extends CommandPanel{

  //TODO: hard coded text
  public static final String TITLE = "Turtle Commands: ";
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(Arrays.asList("Forward", "Backward", "Left",
                                                                      "Right", "SetHeading", "SetTowards", "SetPosition",
                                                                      "PenDown", "PenUp", "ShowTurtle", "HideTurtle", "Home", "ClearScreen"));

  public TurtleCommandPanel(String language, double x) {
    super(language, IDENTIFIERS, TITLE, x, CommonCommands.TOP_PADDING);
  }
}

package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

public class TurtleCommandPanel extends CommandPanel{

  public static final String RESOURCE_FILE_NAME = "English";
  public static final String TITLE = "Turtle Commands: ";
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(Arrays.asList("Forward", "Backward", "Left",
                                                                      "Right", "SetHeading", "SetTowards", "SetPosition",
                                                                      "PenDown", "PenUp", "ShowTurtle", "HideTurtle", "Home", "ClearScreen"));


  public TurtleCommandPanel()
  {
    super(RESOURCE_FILE_NAME, IDENTIFIERS ,TITLE, 0, CommonCommands.TOP_PADDING);
  }
}

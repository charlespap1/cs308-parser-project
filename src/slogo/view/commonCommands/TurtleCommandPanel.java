package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

public class TurtleCommandPanel extends CommandPanel{

  public static final String RESOURCE_FILE_NAME = "CommonCommands";
  public static final String TITLE = "Turtle Commands: ";
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(Arrays.asList("Forward", "Back", "Left",
                                                                      "Right"));

  public TurtleCommandPanel()
  {
    super(RESOURCE_FILE_NAME, IDENTIFIERS ,TITLE);

  }
}

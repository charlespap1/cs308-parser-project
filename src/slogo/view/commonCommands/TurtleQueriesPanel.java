package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

public class TurtleQueriesPanel extends CommandPanel{
  public static final String RESOURCE_FILE_NAME = "English";
  public static final String TITLE = "Turtle Queries: ";
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(
        Arrays.asList("XCoordinate", "YCoordinate", "Heading",
            "IsPenDown", "IsShowing"));


    public TurtleQueriesPanel(double x)
    {
      super(RESOURCE_FILE_NAME, IDENTIFIERS ,TITLE, x, CommonCommands.TOP_PADDING);
    }

}

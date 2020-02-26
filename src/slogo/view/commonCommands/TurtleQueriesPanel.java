package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

public class TurtleQueriesPanel extends CommandPanel{
  public static final String RESOURCE_FILE_NAME = "Queries";
  public static final String TITLE = "Turtle Queries: ";
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(
        Arrays.asList("XCOR", "YCOR", "HEADING",
            "PENDOWNP", "SHOWINGP"));


    public TurtleQueriesPanel(double x)
    {
      super(RESOURCE_FILE_NAME, IDENTIFIERS ,TITLE, x, CommonCommands.TOP_PADDING);
    }

}

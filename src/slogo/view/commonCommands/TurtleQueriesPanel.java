package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Displays commands only associated with turtle queries
 * @author Juliet
 */

public class TurtleQueriesPanel extends CommandPanel{
  //TODO: hard coded text
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(
        Arrays.asList("XCoordinate", "YCoordinate", "Heading",
            "IsPenDown", "IsShowing"));


    public TurtleQueriesPanel(String language, double x) {
      super(language, IDENTIFIERS, x, CommonCommands.TOP_PADDING);
    }

}

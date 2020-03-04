package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Displays commands only associated with boolean operations
 * @author Juliet
 */

public class BooleanOperationsPanel extends CommandPanel{

  //TODO: hard coded text
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(
      Arrays.asList("LessThan", "GreaterThan", "Equal",
          "NotEqual", "And", "Or", "Not"));


  public BooleanOperationsPanel(String language, double x) {
    super(language, IDENTIFIERS, x, CommonCommands.TOP_PADDING);
  }


}

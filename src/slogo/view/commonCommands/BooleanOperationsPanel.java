package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

public class BooleanOperationsPanel extends CommandPanel{

  //TODO: hard coded text
  public static final String TITLE = "Boolean Operations: ";
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(
      Arrays.asList("LessThan", "GreaterThan", "Equal",
          "NotEqual", "And", "Or", "Not"));


  public BooleanOperationsPanel(String language, double x) {
    super(language, IDENTIFIERS ,TITLE, x, CommonCommands.TOP_PADDING);
  }


}

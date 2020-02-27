package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Displays commands only associated with math operations
 * @author Juliet
 */

public class MathOperationsPanel extends CommandPanel{

  // TODO: hard coded text
  public static final String TITLE = "Math Operations: ";
  public static final ArrayList<String> IDENTIFIERS = new ArrayList<>(
      Arrays.asList("Sum", "Difference", "Product",
          "Quotient", "Remainder", "Minus", "Random",
          "Sine", "Cosine", "Tangent", "ArcTangent", "NaturalLog", "Power", "Pi"));

  public MathOperationsPanel(String language, double x) {
    super(language, IDENTIFIERS ,TITLE, x, CommonCommands.TOP_PADDING);
  }
}

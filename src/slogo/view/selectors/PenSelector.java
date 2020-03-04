package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;
import slogo.view.Turtle;

/**
 * Allows the user to choose different pen colors
 * @author Juliet
 */

public class PenSelector extends ColorSelector{

  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(
      Arrays.asList("1", "2", "3", "4", "5", "6"));
  private static final String RESOURCES_PATH = "resources.colors.PenColors";
  //TODO: text hard coded
  public static final String TITLE = "Pen colors: ";

  private Turtle turtle;

  public PenSelector(Turtle turtle, double x, double y) {
    super(x,  y, BACKGROUND_COLORS,  RESOURCES_PATH);
    setColorButtons();
    this.turtle = turtle;
  }

  /**
   * Changes color of turtle pen if button is clicked
   * @param hex
   */
  @Override
  protected void changeAppearance(String hex) {
    Color color = Color.web(hex);
    turtle.changePenColor(color);
  }
}

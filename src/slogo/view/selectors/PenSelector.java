package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;
import slogo.view.Turtle;


public class PenSelector extends ColorSelector{

  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(
      Arrays.asList("Blue", "Green", "White", "Red", "Black", "Magenta"));
  private static final String RESOURCES_PATH = "resources.colors.PenColors";
  //TODO: text hard coded
  public static final String TITLE = "Pen colors: ";

  private Turtle turtle;

  public PenSelector(Turtle turtle, double x, double y) {
    super(TITLE,  x,  y, BACKGROUND_COLORS,  RESOURCES_PATH);
    setColorButtons();
    this.turtle = turtle;
  }

  @Override
  protected void changeAppearance(String hex) {
    Color color = Color.web(hex);
    turtle.changePenColor(color);
  }
}

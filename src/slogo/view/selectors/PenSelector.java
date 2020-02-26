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
  public static final String TITLE = "Pen colors: ";

  private Turtle t;

  public PenSelector(Turtle t, double x, double y)
  {
    super(TITLE,  x,  y, BACKGROUND_COLORS,  RESOURCES_PATH);
    setColorButtons();
    this.t = t;
  }

  @Override
  public void changeSomething(String hex) {
    changePenColor(hex);
  }

  public void changePenColor(String hexColor) {
    Color color = Color.web(hexColor);
    t.changePenColor(color);
  }

}

package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;
import slogo.view.Turtle;


public class PenSelector extends ColorSelector{

  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(
      Arrays.asList("White", "Red", "Magenta", "Blue", "Green", "Black"));
  private static final String RESOURCES = "resources";
  public static final String BACKGROUND_RESOURCE_PACKAGE = RESOURCES + ".commands.BackgroundColors";
  public static final String TITLE = "Pen colors: ";

  private Turtle t;

  public PenSelector(Turtle t, double x, double y)
  {
    super(TITLE,  x,  y, BACKGROUND_COLORS,  BACKGROUND_RESOURCE_PACKAGE);
    //this.t = t;

  }

  @Override
  public void changeSomething(String hex) {
    System.out.println("here");
    changePenColor(hex);
  }

  public void changePenColor(String hexColor) {
    Color color = Color.web(hexColor);
    System.out.println("here");
    //t.changePenColor(color);
  }

}

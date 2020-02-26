package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;
import slogo.view.DrawingCanvas;

public class BackgroundSelector extends ColorSelector{

  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(Arrays.asList("White", "Red", "Magenta", "Blue", "Green", "Black"));
  private static final String RESOURCES_PATH = "resources.colors.BackgroundColors";
  public static final String TITLE = "Background colors: ";

  private DrawingCanvas dc;

  public BackgroundSelector(DrawingCanvas dc, double x, double y)
  {
    super(TITLE,  x,  y, BACKGROUND_COLORS,  RESOURCES_PATH);
    this.dc = dc;
    setColorButtons();

  }

  @Override
  public void changeSomething(String hex)
  {
    changeBackground(hex);
  }

  private void changeBackground(String hexColor) {
    Color color = Color.web(hexColor);
    dc.changeBackground(color);
  }

}

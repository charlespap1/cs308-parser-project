package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;
import slogo.view.DrawingCanvas;

/**
 * Allows user to choose a background color for their drawing canvas
 * @author Juliet
 */

public class BackgroundSelector extends ColorSelector{

  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(Arrays.asList("White", "Red", "Magenta", "Blue", "Green", "Black"));
  private static final String RESOURCES_PATH = "resources.colors.BackgroundColors";
  //TODO: text hard coded
  public static final String TITLE = "Background colors: ";

  private DrawingCanvas canvas;

  public BackgroundSelector(DrawingCanvas canvas, double x, double y) {
    super(TITLE,  x,  y, BACKGROUND_COLORS,  RESOURCES_PATH);
    this.canvas = canvas;
    setColorButtons();
  }

  /**
   * When user clicks a new color, the color of the drawing canvas is changed
   * @param hex
   */
  @Override
  protected void changeAppearance(String hex) {
    Color color = Color.web(hex);
    canvas.changeBackground(color);
  }
}

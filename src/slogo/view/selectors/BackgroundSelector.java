package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import slogo.view.DrawingCanvas;

/**
 * Allows user to choose a background color for their drawing canvas
 * @author Juliet
 */

public class BackgroundSelector extends ColorSelector{

  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
  private static final String RESOURCES_PATH = "resources.colors.BackgroundColors";

  private DrawingCanvas canvas;

  public BackgroundSelector(DrawingCanvas canvas, double x, double y) {
    super(x,  y, BACKGROUND_COLORS,  RESOURCES_PATH);
    this.canvas = canvas;
    setColorButtons();
  }

  /**
   * When user clicks a new color, the color of the drawing canvas is changed
   * @param
   */
  @Override
  public void changeAppearance(int index) {
    // TODO load rgb from index
    String rgb = "10,10,10";
    RGBHelper rgbHelper = new RGBHelper();
    canvas.changeBackground(rgbHelper.getColor(rgb));
  }
}

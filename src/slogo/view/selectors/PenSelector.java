package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import slogo.view.RGBHelper;
import slogo.view.Turtle;

/**
 * Allows the user to choose different pen colors
 * @author Juliet
 */

public class PenSelector extends ColorSelector{

  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(
      Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
  private static final String RESOURCES_PATH = "resources.colors.BackgroundColors";
  //TODO: text hard coded
  public static final String TITLE = "Pen colors: ";

  private Turtle turtle;

  public PenSelector(Turtle turtle, double x, double y) {
    super(x,  y, BACKGROUND_COLORS,  RESOURCES_PATH);
    setColorButtons();
    this.turtle = turtle;
  }

  public void tryColorPicker()
  {
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setValue(Color.CORAL);
    colorPicker.setOnAction((EventHandler) t -> turtle.changePenColor(colorPicker.getValue()));
    myHolder.getChildren().add(colorPicker);
  }


  /**
   * Changes color of turtle pen if button is clicked
   * @param rgb
   */
  @Override
  protected void changeAppearance(String rgb) {
    RGBHelper rgbHelper = new RGBHelper();
    turtle.changePenColor(rgbHelper.getColor(rgb));
  }
}

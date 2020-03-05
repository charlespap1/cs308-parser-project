package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.view.Turtle;

/**
 * Allows the user to choose different pen colors
 * @author Juliet
 */

public class PenSelector extends ColorSelector{

  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(
      Arrays.asList("1", "2", "3", "4", "5", "6"));
  private static final String RESOURCES_PATH = "resources.colors.BackgroundColors";
  //TODO: text hard coded
  public static final String TITLE = "Pen colors: ";

  private List<Turtle> turtles;

  public PenSelector(List<Turtle> turtleList, double x, double y) {
    super(x,  y, BACKGROUND_COLORS,  RESOURCES_PATH);
    setColorButtons();
    turtles = turtleList;
  }

  public void tryColorPicker() {
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setValue(Color.CORAL);
    colorPicker.setOnAction(t ->setPenColor(colorPicker.getValue()));
    myHolder.getChildren().add(colorPicker);
  }


  /**
   * Changes color of turtle pen if button is clicked
   * @param
   */
  @Override
  public void changeAppearance(int index) {
    // TODO get rgb from mapping
    String rgb = "10,10,10";
    Color c = (new RGBHelper()).getColor(rgb);
    setPenColor(c);
  }

  private void setPenColor(Color color){
    //for (Turtle t:turtles) t.changePenColor(color);
  }
}

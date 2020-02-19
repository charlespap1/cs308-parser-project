package slogo.view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

/**
 * This class specifies the attributes of the
 * drawing screen where the turtle lives and
 * new lines are drawn according to the user input
 */
public class DrawingCanvas {

  public static final double CANVAS_TOP_PADDING = 50;
  public static final double CANVAS_BOTTOM_PADDING = 75;
  public static final double CANVAS_SIDE_PADDING = 10;
  public static final double ARC_RADIUS = 10;
  public static final Color BACKGROUND_COLOR = Color.WHITE;
  public static final Color BORDER_COLOR = Color.LIGHTBLUE;

  private double canvasWidth;
  private double canvasHeight;
  private Rectangle myView;

  private List<Line> myLines;

  public DrawingCanvas(double screenWidth, double screenHeight)
  {
    canvasWidth = screenWidth/3 - 2*CANVAS_SIDE_PADDING;
    canvasHeight = screenHeight - CANVAS_TOP_PADDING - CANVAS_BOTTOM_PADDING;
    myLines = new ArrayList<>();
    setBody();
  }

  private void setBody()
  {
    myView = new Rectangle();

    myView.setX(CANVAS_SIDE_PADDING);
    myView.setY(CANVAS_TOP_PADDING);
    myView.setWidth(canvasWidth);
    myView.setHeight(canvasHeight);

    myView.setArcWidth(ARC_RADIUS);
    myView.setArcHeight(ARC_RADIUS);

    myView.setFill(BACKGROUND_COLOR);

    myView.setStroke(BORDER_COLOR);
  }

  /**
   * Allows the canvas to be added to the root of the main class
   * @return
   */

  public Node getView()
  {
    return myView;
  }

  /**
   * These methods allow us to get size of the canvas so
   * we can place the turtle in the middle of it
   * @return
   */
  public double getWidth() { return canvasWidth; }

  public double getHeight() { return canvasHeight; }

  public void changeBackground(Color color)
  {
    myView.setFill(color);
  }

  public void addLine(Line newLine)
  {
    myLines.add(newLine);
  }

  public List<Line> getLines()
  {
    return myLines;
  }



}

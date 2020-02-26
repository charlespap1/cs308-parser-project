package slogo.view;

import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.awt.geom.Point2D;

/**
 * This class holds all of the attributes of our GUI turtle
 * @author Juliet
 */
public class Turtle {
  public static final int TURTLE_IMAGE_SIZE = 30;
  public static final Color DEFAULT_PEN_COLOR = Color.BLACK;
  public static final double DEFAULT_ANGLE = 90;
  public static final double TURTLE_FACTOR = TURTLE_IMAGE_SIZE/2.0;

  private ImageView myTurtleView;
  private double centerX;
  private double centerY;
  private double canvasWidth;
  private double canvasHeight;

  private DoubleProperty x = new SimpleDoubleProperty();
  private DoubleProperty y = new SimpleDoubleProperty();
  private ObjectProperty<Point2D> coordinates = new SimpleObjectProperty<>();
  private DoubleProperty angle = new SimpleDoubleProperty();
  private BooleanProperty penUp = new SimpleBooleanProperty();
  private BooleanProperty visible = new SimpleBooleanProperty();
  private double currX;
  private double currY;

  private Color penColor = DEFAULT_PEN_COLOR;

  public Turtle(Image image, double width, double height)
  {
    canvasWidth = width;
    canvasHeight = height;
    centerX = DrawingCanvas.CANVAS_SIDE_PADDING + canvasWidth/2 - TURTLE_FACTOR;
    centerY = DrawingCanvas.CANVAS_TOP_PADDING + canvasHeight/2 - TURTLE_FACTOR;

    myTurtleView = new ImageView(image);
    myTurtleView.setFitWidth(TURTLE_IMAGE_SIZE);
    myTurtleView.setFitHeight(TURTLE_IMAGE_SIZE);

    myTurtleView.xProperty().bind(x.add(centerX-TURTLE_FACTOR));
    myTurtleView.yProperty().bind(y.add(centerY-TURTLE_FACTOR));
    myTurtleView.rotateProperty().bind(angle);
    myTurtleView.visibleProperty().bind(visible);
  }

  public void setProperties(slogo.model.Turtle turtle) {
    x.bindBidirectional(turtle.turtleXProperty());
    y.bindBidirectional(turtle.turtleYProperty());
    angle.bindBidirectional(turtle.turtleAngleProperty());
    penUp.bind(turtle.penUpProperty());
    visible.bind(turtle.visibleProperty());
    coordinates.bindBidirectional(turtle.pointProperty());
    currX = x.getValue();
    currY = y.getValue();
    returnTurtleToDefault();
  }

  /**
   * Allows Main class to be put turtle back in center
   * with default values
   */
  public void returnTurtleToDefault() {
    x.setValue(0);
    y.setValue(0);
    currX = 0;
    currY = 0;
    coordinates.set(new Point2D.Double(0, 0));
    angle.setValue(DEFAULT_ANGLE);
  }

  public Line drawLineAndBound(){
    Line line = null;
    if (outOfBounds()) fixBounding();
    else line = drawLine();
    return line;
  }

  /**
   * Allows the Main class to get the image body of the turtle to display
   * @return
   */
  public Node getView () { return myTurtleView; }

  /**
   * Changes image of turtle
   * @param image
   */
  public void changeImage(Image image) { myTurtleView.setImage(image); }

  public void changePenColor(Color color) { penColor = color; }

  private boolean outOfBounds(){
    return x.getValue() > canvasWidth/2 || x.getValue() < -canvasWidth/2+TURTLE_IMAGE_SIZE ||
            y.getValue() > canvasHeight/2 || y.getValue() < -canvasHeight/2+TURTLE_IMAGE_SIZE;
  }

  public Line drawLine(){
    Line line = null;
    if (!penUp.getValue()) {
      line = new Line(currX + centerX, currY + centerY, x.getValue() + centerX, y.getValue() + centerY);
      line.setStroke(penColor);
    }
    currX = x.getValue();
    currY = y.getValue();
    return line;
  }

  private void fixBounding(){
    double xDistanceOutOfBounds = 0;
    if (x.getValue()>canvasWidth/2) xDistanceOutOfBounds = x.getValue()-canvasWidth/2;
    else if (x.getValue()<-canvasWidth/2+TURTLE_IMAGE_SIZE) xDistanceOutOfBounds = Math.abs(x.getValue() + canvasWidth/2);
    double yDistanceOutOfBounds = 0;
    if (y.getValue()>canvasHeight/2) yDistanceOutOfBounds = y.getValue()-canvasHeight/2;
    else if (y.getValue()<-canvasHeight/2+TURTLE_IMAGE_SIZE) yDistanceOutOfBounds = Math.abs(y.getValue() + canvasHeight/2);
    hitsSide(xDistanceOutOfBounds>yDistanceOutOfBounds);
  }

  private void hitsSide(boolean verticalSide){
    double deltaX = x.getValue() - currX;
    double deltaY = y.getValue() - currY;
    double val1 = (verticalSide) ? ((x.getValue()>canvasWidth/2) ? canvasWidth/2 : -canvasWidth/2+TURTLE_IMAGE_SIZE) : ((y.getValue()>canvasHeight/2) ? canvasHeight/2 : -canvasHeight/2+TURTLE_IMAGE_SIZE);
    double delta1 = (verticalSide) ? val1 - currX : val1 - currY;
    double delta2 = delta1 * ((verticalSide) ? deltaY/deltaX : deltaX/deltaY);
    double val2 = delta2 + ((verticalSide) ? currY : currX);

    x.setValue((verticalSide) ? val1 : val2);
    y.setValue((verticalSide) ? val2 : val1);
    coordinates.set(new Point2D.Double((verticalSide) ? val1 : val2, (verticalSide) ? val2 : val1));
  }
}

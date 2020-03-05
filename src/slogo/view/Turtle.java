package slogo.view;

import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * This class holds all of the attributes of our GUI turtle
 * @author Juliet, Natalie
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
  private BooleanProperty active = new SimpleBooleanProperty();
  private double currX;
  private double currY;
  private int penThickness = 1;

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

  /**
   * Allows turtle's properties to be binded
   * to the backend turtle so the turtle will update as soon
   * as an instruciton is parsed
   * @param turtle
   */
  public void setProperties(slogo.model.Turtle turtle) {
    x.bindBidirectional(turtle.turtleXProperty());
    y.bindBidirectional(turtle.turtleYProperty());
    angle.bindBidirectional(turtle.turtleAngleProperty());
    penUp.bindBidirectional(turtle.penUpProperty());
    visible.bind(turtle.visibleProperty());
    coordinates.bindBidirectional(turtle.pointProperty());
    currX = x.getValue();
    currY = y.getValue();
    active.bindBidirectional(turtle.activeProperty());
    active.addListener(e -> {
      if (active.getValue()) myTurtleView.setOpacity(1);
      else myTurtleView.setOpacity(0.5);
    });
    returnTurtleToDefault();
    myTurtleView.setOnMouseClicked(e -> active.setValue(!active.getValue()));
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

  /**
   * Draws line as long as turtle is in bounds
   * @return
   */
  public Line drawLineAndBound(){
    Line line = null;
    if (outOfBounds()) fixBounding();
    else line = drawLine();
    return line;
  }

  public double getXPos() {
    return x.getValue();
  }

  public void setLocation(double x, double y) {
    this.x.setValue(x);
    this.y.setValue(y);
    coordinates.setValue(new Point2D.Double(x, y));
  }

  public double getYPos() {
    return y.getValue();
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

  public void setPenUp(boolean isPenUp)
  {
    this.penUp.set(isPenUp);
  }

  public void setThickness(int newThickness)
  {
    penThickness = newThickness;
  }

  public boolean getPenUp()
  {
    return this.penUp.get();
  }

  private Line drawLine(){
    Line line = null;
    if (!penUp.getValue()) {
      line = new Line(currX + centerX, currY + centerY, x.getValue() + centerX, y.getValue() + centerY);
      line.setStrokeWidth(penThickness);
    }
    currX = x.getValue();
    currY = y.getValue();
    return line;
  }

  private boolean outOfBounds(){
    return x.getValue() > canvasWidth/2 || x.getValue() < -canvasWidth/2+TURTLE_IMAGE_SIZE ||
            y.getValue() > canvasHeight/2 || y.getValue() < -canvasHeight/2+TURTLE_IMAGE_SIZE;
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

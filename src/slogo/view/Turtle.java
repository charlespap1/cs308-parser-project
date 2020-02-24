package slogo.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.State;

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
  private DoubleProperty angle = new SimpleDoubleProperty();
  private BooleanProperty penUp = new SimpleBooleanProperty();
  private BooleanProperty visible = new SimpleBooleanProperty();
  private double currX;
  private double currY;

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
    currX = x.getValue();
    currY = y.getValue();
    returnTurtleToDefault();
  }

  public void drawLineAndBound(Group root, DrawingCanvas canvas){
    System.out.println("new method call");
    if (outOfBounds()){
      System.out.println("inbounds: "+false);
      fixBounding();
    }
    else {
      System.out.println("inbounds: "+true);
      Line line = drawLine();
      if (line != null) {
        root.getChildren().add(line);
        canvas.addLine(line);
      }
    }
  }

  private boolean outOfBounds(){
    return Math.abs(x.getValue()) > canvasWidth/2 || Math.abs(y.getValue()) > canvasHeight/2;
  }

  /**
   * Allows Main class to be put turtle back in center
   * with default values
   */
  public void returnTurtleToDefault() {
    x.setValue(0);
    y.setValue(0);
    angle.setValue(DEFAULT_ANGLE);
  }

  public Line drawLine(){
    System.out.println("drawLine called");
    Line line = null;
    if (!penUp.getValue()) {
      line = new Line(currX + centerX, currY + centerY, x.getValue() + centerX, y.getValue() + centerY);
    }
    currX = x.getValue();
    currY = y.getValue();
    return line;
  }

  private void fixBounding(){
    double xDistanceOutOfBounds = 0;
    if (x.getValue()>canvasWidth/2) xDistanceOutOfBounds = x.getValue()-canvasWidth/2;
    else if (x.getValue()<-canvasWidth/2) xDistanceOutOfBounds = Math.abs(x.getValue() + canvasWidth/2);
    double yDistanceOutOfBounds = 0;
    if (y.getValue()>canvasHeight/2) yDistanceOutOfBounds = y.getValue()-canvasHeight/2;
    else if (y.getValue()<-canvasHeight/2) yDistanceOutOfBounds = Math.abs(y.getValue() + canvasHeight/2);
    System.out.println("x dist out: "+xDistanceOutOfBounds + " y dist out: "+yDistanceOutOfBounds);
    if (xDistanceOutOfBounds>yDistanceOutOfBounds) stopAlongVerticalSide();
    else stopAlongHorizontalSide();
  }

  private void stopAlongVerticalSide(){
    System.out.println("vertical side");
    double deltaX = x.getValue() - currX;
    double deltaY = y.getValue() - currY;
    double xDist = 0;
    double xVal = 0;
    if (x.getValue()>canvasWidth/2) {
      xDist = canvasWidth/2 - currX;
      xVal = canvasWidth/2;
      System.out.println("x too big");
    }
    if (x.getValue()<(-1*canvasWidth/2)) {
      xDist = -canvasWidth/2 - currY;
      xVal = -canvasWidth/2;
      System.out.println("x too small");
    }
    System.out.println("x: "+x.get());
    double yDist = deltaY/deltaX*xDist;
    x.setValue(xVal);
    System.out.println(currY + " currY, yDist: "+yDist);
    y.setValue(currY+yDist);

    System.out.println("x: "+x.getValue() + " y: " + y.getValue());
  }

  private void stopAlongHorizontalSide(){
    System.out.println("horiz side");
    double deltaX = x.getValue() - currX;
    double deltaY = y.getValue() - currY;
    double yDist = 0;
    double yVal = 0;
    if (y.getValue()>canvasHeight/2) {
      yDist = canvasHeight/2-currY;
      yVal = canvasHeight/2;
      System.out.println("y too big");
    }
    if (y.getValue()<-canvasHeight/2) {
      yDist = -canvasHeight/2-currY;
      yVal = -canvasHeight/2;
      System.out.println("y too small");
    }
    double xDist = deltaX/deltaY*yDist;
    x.setValue(currX+xDist);
    y.setValue(yVal);
  }

  /**
   * Will be needed when we change turtle images
   * @param image
   */
  public void changeImage(Image image)
  {
    myTurtleView.setImage(image);
  }

  /**
   * Allows the Main class to get the image body of the turtle to display
   * @return
   */
  public Node getView () {
    return myTurtleView;
  }
}

package slogo.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

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

  private DoubleProperty x = new SimpleDoubleProperty();
  private DoubleProperty y = new SimpleDoubleProperty();
  private DoubleProperty angle = new SimpleDoubleProperty();
  private BooleanProperty penUp = new SimpleBooleanProperty();
  private BooleanProperty visible = new SimpleBooleanProperty();
  private double currX;
  private double currY;

  private Color penColor = DEFAULT_PEN_COLOR;

  public Turtle(Image image, double canvasWidth, double canvasHeight)
  {
    centerX = DrawingCanvas.CANVAS_SIDE_PADDING + canvasWidth/2 - TURTLE_FACTOR;
    centerY = DrawingCanvas.CANVAS_TOP_PADDING + canvasHeight/2 - TURTLE_FACTOR;

    myTurtleView = new ImageView(image);
    myTurtleView.setFitWidth(TURTLE_IMAGE_SIZE);
    myTurtleView.setFitHeight(TURTLE_IMAGE_SIZE);

    //this binding should take care of all changes in x and y and angle later on
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
    currX = x.getValue() + centerX;
    currY = y.getValue() + centerY;
    returnTurtleToDefault();
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
    Line line = null;
    if (!penUp.getValue()) {
      line = new Line(currX, currY, x.getValue() + centerX, y.getValue() + centerY);
      line.setStroke(penColor);
    }
    currX = x.getValue() + centerX;
    currY = y.getValue() + centerY;
    return line;
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

  public void changePenColor(Color color)
  {
    penColor = color;
    System.out.println("here");
  }

}

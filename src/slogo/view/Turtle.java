package slogo.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
  public static final double DEFAULT_ANGLE = 0;
  public static final double TURTLE_FACTOR = TURTLE_IMAGE_SIZE/2;

  private double canvasWidth;
  private double canvasHeight;
  private double canvasTopPadding;
  private double canvasLeftPadding;

  private double centerX;
  private double centerY;

  private ImageView myView;
  private double xPos;
  private double yPos;

  private DoubleProperty x;
  private DoubleProperty y;
  private DoubleProperty angle;
  private BooleanProperty penUp;
  private double currX;
  private double currY;

  public Turtle(Image image, double canvasWidth, double canvasHeight)
  {
    canvasLeftPadding = DrawingCanvas.CANVAS_SIDE_PADDING;
    canvasTopPadding = DrawingCanvas.CANVAS_TOP_PADDING;

    myView = new ImageView(image);

    myView.setFitWidth(TURTLE_IMAGE_SIZE);
    myView.setFitHeight(TURTLE_IMAGE_SIZE);

    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;




    x = new SimpleDoubleProperty();
    y = new SimpleDoubleProperty();
    angle = new SimpleDoubleProperty();
    penUp = new SimpleBooleanProperty();
    setDefaultValues();

    //this binding should take care of all changes in x and y and angle later on
    myView.xProperty().bind(x.add(-TURTLE_FACTOR));
    myView.yProperty().bind(y.add(-TURTLE_FACTOR));
    myView.rotateProperty().bind(angle);
  }

  public void setProperties(slogo.model.Turtle turtle){
    x.bindBidirectional(turtle.turtleXProperty());
    y.bindBidirectional(turtle.turtleYProperty());
    angle.bindBidirectional(turtle.turtleAngleProperty());
    penUp.bindBidirectional(turtle.penUpProperty());
    currX = x.getValue();
    currY = y.getValue();

    y.addListener((o, oldVal, newVal) -> {
      System.out.println("turtle has changed!");
      // x, y, angle, and penUp will update automatically w binding. also if we change them here, it should
      // also change the values in model.turtle since the binding is bidirectional
      // so once y changes, you know all 3 others are changed and you can draw a line (or not) and update currx and
      // curry, which exist specifically for line drawing purposes
      // DELETE THIS COMMENT LATER
      drawLine();
    });
    setDefaultValues();
  }

  public String getCommand(){
    return "x pos: " + x.getValue() + ", y pos: " + y.getValue() + ", angle: " + angle.getValue() + ", pen up: " + penUp.getValue();
  }

  //new drawline method simpler, replace old one with this once we're done transitioning to props
  public Line drawLine(){
    Line line = null;
    if (!penUp.getValue()) {
      line = new Line (currX, currY, x.getValue(), y.getValue());
    }
    currX = x.getValue();
    currY = y.getValue();
    return line;
  }

  private void setDefaultValues()
  {
    centerX = canvasLeftPadding + canvasWidth/2;
    centerY = canvasTopPadding + canvasHeight/2;

    // this should be all we need to change to reset turtle in front and back end
    x.setValue(centerX);
    y.setValue(centerY);
    angle.setValue(DEFAULT_ANGLE);
  }

  /**
   * Will be needed when we change turtle images
   * @param image
   */

  public void changeImage(Image image)
  {
    myView.setImage(image);
  }

  /**
   * Allows the Main class to get the image body of the turtle to display
   * @return
   */
  public Node getView () {
    return myView;
  }

  public Line drawLine(State nextState)
  {
    Line line = new Line (xPos, yPos, nextState.getX() + xPos, nextState.getY() + yPos);
    //line.setStroke(nextState.getPenColor());
    return line;
  }

  /**
   * Allows Main class to be put turtle back in center
   * with default values
   */
  public void returnTurtleToDefault()
  {
    setDefaultValues();
  }



}

package slogo.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.State;

/**
 * This class holds all of the attributes of our GUI turtle
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

  private ImageView myView;
  private State myState;
  private Color penColor;

  public Turtle(Image image, double canvasWidth, double canvasHeight)
  {
    canvasLeftPadding = DrawingCanvas.CANVAS_SIDE_PADDING;
    canvasTopPadding = DrawingCanvas.CANVAS_TOP_PADDING;

    myView = new ImageView(image);

    myView.setFitWidth(TURTLE_IMAGE_SIZE);
    myView.setFitHeight(TURTLE_IMAGE_SIZE);


    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;

    setDefaultValues();

  }

  private void setDefaultValues()
  {
    double x = canvasLeftPadding + canvasWidth/2;
    double y = canvasTopPadding + canvasHeight/2;

    myState = new State (x, y, false, DEFAULT_ANGLE);

    myView.setX(myState.getX() - 2*TURTLE_FACTOR);
    myView.setY(myState.getY() - 2*TURTLE_FACTOR);
    myView.setRotate(DEFAULT_ANGLE);

    this.penColor = DEFAULT_PEN_COLOR;
  }

  /**
   * Allows the Main class to get the image body of the turtle to display
   * @return
   */
  public Node getView () {
    return myView;
  }

  /**
   * Updates the location or attributes of the turtle
   * @param nextState
   */
  public Line update(State nextState, Group root)
  {
    Line newLine = null;
    if(needToDrawLine(nextState))
    {
      newLine = drawLine(nextState);
      root.getChildren().add(newLine);
    }
    myState = nextState;
    myView.setX(myState.getX() - TURTLE_FACTOR);
    myView.setY(myState.getY() - TURTLE_FACTOR);
    myView.setRotate(nextState.getAngleFacing());

    root.getChildren().remove(myView);
    root.getChildren().add(myView);

    return newLine;
  }

  private boolean needToDrawLine(State nextState)
  {
    return ((myState.getX() != nextState.getX()) || (myState.getY() != nextState.getY())) && !nextState.isPenUp();
  }

  private Line drawLine(State nextState)
  {
    Line line = new Line (myState.getX(), myState.getY(), nextState.getX(), nextState.getY());
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

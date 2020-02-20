package slogo.view;

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
    centerX = canvasLeftPadding + canvasWidth/2;
    centerY = canvasTopPadding + canvasHeight/2;

    xPos = centerX;
    yPos = centerY;

    myView.setX(xPos - TURTLE_FACTOR);
    myView.setY(yPos - TURTLE_FACTOR);
    myView.setRotate(DEFAULT_ANGLE);
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

  /**
   * Updates the location or attributes of the turtle
   * @param nextState
   */
  public Line update(State nextState)
  {
    Line newLine = null;
    if(!nextState.isPenUp())
    {
      newLine = drawLine(nextState);
    }
    xPos = xPos + nextState.getX();
    yPos = yPos + nextState.getY();

    myView.setX(xPos - TURTLE_FACTOR);
    myView.setY(yPos - TURTLE_FACTOR);
    myView.setRotate(nextState.getAngleFacing());

    return newLine;
  }


  private Line drawLine(State nextState)
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

package slogo.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * This class holds all of the attributes of our GUI turtle
 */

public class Turtle {
  public static final int TURTLE_IMAGE_SIZE = 30;

  private double canvasWidth;
  private double canvasHeight;
  private double canvasTopPadding;
  private double canvasLeftPadding;

  private ImageView myView;
  private double myX;
  private double myY;
  private boolean penUp;
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
    this.myX = canvasLeftPadding + canvasWidth/2 - TURTLE_IMAGE_SIZE/2;
    this.myY = canvasTopPadding + canvasHeight/2 - TURTLE_IMAGE_SIZE/2;

    myView.setX(myX);
    myView.setY(myY);

    this.penUp = false;
    this.penColor = Color.BLACK;
  }

  /**
   * Allows the Main class to get the image body of the turtle to display
   * @return
   */
  public Node getView () {
    return myView;
  }

}

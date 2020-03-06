package slogo.view;

import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.awt.geom.Point2D;

/**
 * This class holds all of the attributes of our GUI turtle
 * @author Juliet, Natalie
 */
public class Turtle {
  public static final int TURTLE_IMAGE_SIZE = 30;
  public static final double DEFAULT_ANGLE = 90;
  public static final double TURTLE_FACTOR = TURTLE_IMAGE_SIZE/2.0;
  public static final String STATE_POPUP_STYLE = "state-popup";

  private ImageView myTurtleView;
  private double centerX;
  private double centerY;
  private double canvasWidth;
  private double canvasHeight;

  private DoubleProperty x = new SimpleDoubleProperty();
  private DoubleProperty y = new SimpleDoubleProperty();
  private ObjectProperty<Point2D> coordinates = new SimpleObjectProperty<>();
  private DoubleProperty angle = new SimpleDoubleProperty();
  private BooleanProperty visible = new SimpleBooleanProperty();
  private double id;
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

  /**
   * Allows turtle's properties to be binded
   * to the backend turtle so the turtle will update as soon
   * as an instruciton is parsed
   * @param turtle
   */
  public void setProperties(slogo.model.Turtle turtle) {
    id = turtle.getId();
    x.bindBidirectional(turtle.turtleXProperty());
    y.bindBidirectional(turtle.turtleYProperty());
    angle.bindBidirectional(turtle.turtleAngleProperty());
    visible.bind(turtle.visibleProperty());
    coordinates.bindBidirectional(turtle.pointProperty());
    currX = x.getValue();
    currY = y.getValue();
    turtle.activeProperty().addListener(e -> {
      if (turtle.activeProperty().getValue()) {
        myTurtleView.toFront();
        myTurtleView.setOpacity(1);
      }
      else myTurtleView.setOpacity(0.5);
    });
    returnTurtleToDefault();
    myTurtleView.setOnMouseClicked(e -> turtle.activeProperty().setValue(!turtle.activeProperty().getValue()));
  }


  public VBox buildPopup(){
    VBox stateBox = new VBox();
    Text xtext = new Text();
    Text ytext = new Text();
    Text angletext = new Text();
    stateBox.getChildren().addAll(new Label("ID: "+id), xtext, ytext, angletext);
    stateBox.setVisible(false);
    stateBox.getStyleClass().add(STATE_POPUP_STYLE);
    myTurtleView.setOnMouseEntered(e->{
      xtext.setText("x: "+ Math.round(x.get()));
      ytext.setText("y: "+ Math.round(-y.get()));
      angletext.setText("Θ: "+ (Math.floorMod(Math.round(angle.get()-DEFAULT_ANGLE), 360)));
      stateBox.setLayoutX(myTurtleView.getX()+TURTLE_IMAGE_SIZE);
      stateBox.setLayoutY(myTurtleView.getY()+TURTLE_IMAGE_SIZE);
      stateBox.setVisible(true);
      stateBox.toFront();
    });
    myTurtleView.setOnMouseExited(e-> stateBox.setVisible(false));
    return stateBox;
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
  public Line drawLineAndBound(boolean penUp){
    Line line = null;
    if (outOfBounds()) fixBounding();
    else line = drawLine(penUp);
    return line;
  }

  /**
   * Allows the Main class to get the image body of the turtle to display
   * @return
   */
  public Node getView () { return myTurtleView; }

  /**
   * Changes image of turtle, angle and allows for getting the angle.
   * All needed for frontend movement to link with backend
   * @param image
   */
  public void changeImage(Image image) { myTurtleView.setImage(image); }
  public void setAngle(double newAngle) { angle.set(newAngle); }
  public double getAngle() { return angle.get(); }

  private Line drawLine(boolean penUp){
    Line line = null;
    if (!penUp) line = new Line(currX + centerX, currY + centerY, x.getValue() + centerX, y.getValue() + centerY);
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

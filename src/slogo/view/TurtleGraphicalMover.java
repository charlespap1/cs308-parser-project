package slogo.view;

import java.util.Objects;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TurtleGraphicalMover {

  public static final int MOVEMENT_VALUE = 10;
  public static final int BOX_SPACING = 5;
  public static final int MAJOR_BOX_SPACING = 15;
  public static final String ARROW_IMAGE_FILE = "arrow.png";
  public static final int BUTTON_WIDTH = 20;

  private Turtle myTurtle;
  private VBox myButtonHolder;
  private HBox myMiddleButtons;
  private VBox myPenElements;

  private HBox myHolder;

  private Button up;
  private Button left;
  private Button right;
  private Button down;
  private Slider increaseThickness;
  private RadioButton changePenUp;
  private RadioButton changePenDown;

  public TurtleGraphicalMover(Turtle t, double x, double y)
  {
    myTurtle = t;
    myHolder = new HBox(MAJOR_BOX_SPACING);
    myButtonHolder = new VBox(BOX_SPACING);
    myMiddleButtons = new HBox(BOX_SPACING);
    myPenElements = new VBox(BOX_SPACING);

    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);

    myButtonHolder.setAlignment(Pos.CENTER);
    setTurtleButtons();
    setPenElements();

    myHolder.getChildren().addAll(myButtonHolder, myPenElements);
  }

  public Node getView()
  {
    return myHolder;
  }

  private void setPenElements()
  {
    increaseThickness = new Slider(1, 5, 1);
    increaseThickness.setShowTickLabels(true);
    increaseThickness.setShowTickMarks(true);
    increaseThickness.setMajorTickUnit(5);
    increaseThickness.setMinorTickCount(4);
    increaseThickness.snapToTicksProperty().setValue(true);
    increaseThickness.setOnMouseClicked(e -> setThickness((int) Math.round(increaseThickness.getValue())));

    setUpToggleViewer();
    HBox buttonHolder = new HBox(MAJOR_BOX_SPACING);
    buttonHolder.getChildren().addAll(changePenUp, changePenDown);

    myPenElements.getChildren().addAll(increaseThickness, buttonHolder);
  }

  public void setPenLabelProperty(StringProperty penUp, StringProperty penDown){
    changePenUp.textProperty().bind(penUp);
    changePenDown.textProperty().bind(penDown);
  }

  private void setUpToggleViewer()
  {
    changePenUp = new RadioButton();
    changePenUp.setOnAction(e -> setPenUp(true));

    changePenDown = new RadioButton();
    changePenDown.setOnAction(e -> setPenUp(false));

    ToggleGroup radioGroup = new ToggleGroup();

    changePenUp.setToggleGroup(radioGroup);
    changePenDown.setToggleGroup(radioGroup);

    if(myTurtle.getPenUp())
    {
      changePenUp.setSelected(true);
    }
    else
    {
      changePenDown.setSelected(true);
    }

  }


  private void setTurtleButtons()
  {

    up = new Button();
    up.setOnAction(e -> moveTurtleUp());
    ImageView upImage = getButtonPic();
    upImage.setRotate(-90);
    up.setGraphic(upImage);

    left = new Button();
    left.setOnAction(e -> moveTurtleLeft());
    ImageView leftImage = getButtonPic();
    leftImage.setRotate(180);
    left.setGraphic(leftImage);

    down = new Button();
    down.setOnAction(e -> moveTurtleDown());
    ImageView downImage = getButtonPic();
    downImage.setRotate(90);
    down.setGraphic(downImage);

    right = new Button();
    right.setOnAction(e -> moveTurtleRight());
    ImageView rightImage = getButtonPic();
    right.setGraphic(rightImage);

    myMiddleButtons.getChildren().addAll(left, right);
    myButtonHolder.getChildren().addAll(up, myMiddleButtons, down);
  }

  private ImageView getButtonPic()
  {
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(ARROW_IMAGE_FILE)));
    ImageView iv = new ImageView(image);
    iv.setFitWidth(BUTTON_WIDTH);
    iv.setFitHeight(BUTTON_WIDTH);
    iv.setPreserveRatio(true);
    return iv;
  }

  private void setPenUp(boolean isPenUp)
  {
    myTurtle.setPenUp(isPenUp);
  }

  private void setThickness(int thickness)
  {
    myTurtle.setThickness(thickness);
  }


  private void moveTurtleUp()
  {
    double y = myTurtle.getYPos() - MOVEMENT_VALUE;
    setTurtle(myTurtle.getXPos(), y);
  }
  private void moveTurtleDown()
  {
    double y = myTurtle.getYPos() + MOVEMENT_VALUE;
    setTurtle(myTurtle.getXPos(), y);
  }

  private void moveTurtleLeft()
  {
    double x = myTurtle.getXPos() - MOVEMENT_VALUE;
    setTurtle(x, myTurtle.getYPos());
  }

  private void moveTurtleRight()
  {
    double x = myTurtle.getXPos() + MOVEMENT_VALUE;
    setTurtle(x, myTurtle.getYPos());
  }

  private void setTurtle(double x, double y)
  {
    myTurtle.setLocation(x, y);
  }


}

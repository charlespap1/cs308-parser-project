package slogo.view;

import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TurtleGraphicalMover {

  public static final int MOVEMENT_VALUE = 10;
  public static final int BOX_SPACING = 5;
  public static final String ARROW_IMAGE_FILE = "arrow.png";
  public static final int BUTTON_WIDTH = 20;

  private Turtle myTurtle;
  private VBox myHolder;
  private HBox myMiddleButtons;
  private Button up;
  private Button left;
  private Button right;
  private Button down;

  public TurtleGraphicalMover(Turtle t, double x, double y)
  {
    myTurtle = t;
    myHolder = new VBox(BOX_SPACING);
    myMiddleButtons = new HBox(BOX_SPACING);
    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);
    myHolder.setAlignment(Pos.CENTER);
    setButtons();
  }

  public Node getView()
  {
    return myHolder;
  }

  private void setButtons()
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
    myHolder.getChildren().addAll(up, myMiddleButtons, down);
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

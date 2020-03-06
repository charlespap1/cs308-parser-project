package slogo.view;

import javafx.beans.property.StringProperty;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Text;

import java.util.Objects;

public class TurtleGraphicalMover implements StaticViewElement{
  public static final int BOX_SPACING = 5;
  public static final int MAJOR_BOX_SPACING = 15;
  public static final String ARROW_IMAGE_FILE = "arrow.png";
  public static final int BUTTON_WIDTH = 20;

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
  private Text myThicknessText = new Text();

  public TurtleGraphicalMover(double x, double y)
  {
    //myTurtle = t;
    myHolder = new HBox(MAJOR_BOX_SPACING);
    myButtonHolder = new VBox(BOX_SPACING);
    myMiddleButtons = new HBox(BOX_SPACING);
    myPenElements = new VBox(BOX_SPACING);

    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);

    myButtonHolder.setAlignment(Pos.CENTER);
    myPenElements.setAlignment(Pos.CENTER);
    setTurtleButtons();
    setPenElements();

    myHolder.getChildren().addAll(myButtonHolder, myPenElements);
  }

  public void setUpButton(EventHandler<ActionEvent> action, LineManager lineManager){
    up.addEventHandler(ActionEvent.ACTION, e -> lineManager.checkMovingFromButtons());
    up.addEventHandler(ActionEvent.ACTION, action);
  }
  public void setDownButton(EventHandler<ActionEvent> action, LineManager lineManager){
    down.addEventHandler(ActionEvent.ACTION, e -> lineManager.checkMovingFromButtons());
    down.addEventHandler(ActionEvent.ACTION, action);
  }
  public void setLeftButton(EventHandler<ActionEvent> action){ left.setOnAction(action); }
  public void setRightButton(EventHandler<ActionEvent> action){ right.setOnAction(action); }

  public Node getView()
  {
    return myHolder;
  }

  public void setSlider(double val)
  {
    increaseThickness.setValue(val);
  }

  @Override
  public void setTitleProperty(StringProperty sp) {
    myThicknessText.textProperty().bind(sp);
    myPenElements.getChildren().add(0, myThicknessText);
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

//    if(myTurtle.getPenUp())
//    {
//      changePenUp.setSelected(true);
//    }
//    else
//    {
//      changePenDown.setSelected(true);
//    }

  }

  private void setTurtleButtons()
  {
    up = new Button();
    ImageView upImage = getButtonPic();
    upImage.setRotate(-90);
    up.setGraphic(upImage);

    left = new Button();
    ImageView leftImage = getButtonPic();
    leftImage.setRotate(180);
    left.setGraphic(leftImage);

    down = new Button();
    ImageView downImage = getButtonPic();
    downImage.setRotate(90);
    down.setGraphic(downImage);

    right = new Button();
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
    //myTurtle.setPenUp(isPenUp);
  }

  private void setThickness(int thickness)
  {
    //myTurtle.setThickness(thickness);
  }

  private void setTurtle(double x, double y)
  {
    //myTurtle.setLocation(x, y);
  }


}

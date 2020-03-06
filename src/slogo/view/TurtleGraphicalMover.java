package slogo.view;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
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
import slogo.controller.DirectExecutor;

import java.util.Objects;

/**
 * This class allows the user to press buttons to move the turtle and
 * change its pen attributes
 * rather than always having to put in commands
 * @author Juliet, Natalie
 */
public class TurtleGraphicalMover implements StaticViewElement{
  public static final int BOX_SPACING = 5;
  public static final int MAJOR_BOX_SPACING = 15;
  public static final String ARROW_IMAGE_FILE = "arrow.png";
  public static final int BUTTON_WIDTH = 20;
  public static final int DEFAULT_PEN_WIDTH = 1;
  public static final int MOVEMENT_VALUE = 30;
  public static final int FIRST_ELEMENT_IN_LIST = 0;

  private StringProperty forwardString = new SimpleStringProperty();
  private StringProperty rightString = new SimpleStringProperty();
  private StringProperty backString = new SimpleStringProperty();
  private StringProperty leftString = new SimpleStringProperty();


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

  private boolean penUp = false;
  private double penWidth = DEFAULT_PEN_WIDTH;

  public TurtleGraphicalMover(double x, double y) {
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

  /**
   * Sets radio buttons
   * @param isPenUp
   */
  public void setPenUp(boolean isPenUp) {
    if (isPenUp){
      changePenUp.setSelected(true);
    } else changePenDown.setSelected(true);
    penUp = isPenUp;
  }

  /**
   * Getters and setters needed to adjust visual radio buttons and sliders
   * @return
   */
  public boolean getPenUp() { return penUp; }
  public void setPenWidth(double val) {
    penWidth = val;
    increaseThickness.setValue(val);
  }
  public double getPenWidth() { return penWidth; }

  /**
   * Sets buttons to be linked with the line manager so we can
   * add, delete, undo and redo lines created by graphical movement
   * @param executor
   * @param lineManager
   */
  public void setButtons(DirectExecutor executor, LineManager lineManager){
    up.setOnAction(e ->{
      lineManager.newProgram();
      executor.execute(forwardString.get() + " " + MOVEMENT_VALUE);
    });
    down.setOnAction(e -> {
      lineManager.newProgram();
      executor.execute(backString.get() + " " + MOVEMENT_VALUE);
    });
    right.setOnAction(e -> {
      lineManager.newProgram();
      executor.execute(rightString.get() + " " + MOVEMENT_VALUE);
    });
    left.setOnAction(e ->{
      lineManager.newProgram();
      executor.execute(leftString.get() + " " + MOVEMENT_VALUE);
    });
  }

  /**
   * Allows us to get a node of all of the view elements associated with
   * a graphical mover
   * @return
   */
  @Override
  public Node getView()
  {
    return myHolder;
  }

  /**
   * Dynamically sets thickness text according to language
   * @param sp
   */
  @Override
  public void setTitleProperty(List<StringProperty> sp) {
    myThicknessText.textProperty().bind(sp.get(FIRST_ELEMENT_IN_LIST));
    myPenElements.getChildren().add(FIRST_ELEMENT_IN_LIST, myThicknessText);
    changePenUp.textProperty().bind(sp.get(1));
    changePenDown.textProperty().bind(sp.get(2));
  }

  /**
   * Allows for binding with backend movement
   * @param forward
   * @param right
   * @param back
   * @param left
   */
  public void setCommandNameProperties(StringProperty forward, StringProperty right, StringProperty back, StringProperty left){
    forwardString.bind(forward);
    rightString.bind(right);
    backString.bind(back);
    leftString.bind(left);
  }

  private void setPenElements() {
    increaseThickness = new Slider(1, 5, 1);
    increaseThickness.setShowTickLabels(true);
    increaseThickness.setShowTickMarks(true);
    increaseThickness.setMajorTickUnit(5);
    increaseThickness.setMinorTickCount(4);
    increaseThickness.snapToTicksProperty().setValue(true);
    increaseThickness.setOnMouseClicked(e -> setPenWidth(increaseThickness.getValue()));

    setUpToggleViewer();
    HBox buttonHolder = new HBox(MAJOR_BOX_SPACING);
    buttonHolder.getChildren().addAll(changePenUp, changePenDown);
    myPenElements.getChildren().addAll(increaseThickness, buttonHolder);
  }


  private void setUpToggleViewer() {
    changePenUp = new RadioButton();
    changePenUp.setOnAction(e -> setPenUp(true));

    changePenDown = new RadioButton();
    changePenDown.setOnAction(e -> setPenUp(false));

    ToggleGroup radioGroup = new ToggleGroup();
    changePenUp.setToggleGroup(radioGroup);
    changePenDown.setToggleGroup(radioGroup);
  }

  private void setTurtleButtons() {
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
}

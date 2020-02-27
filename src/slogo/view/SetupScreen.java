package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.view.commonCommands.CommonCommands;
import slogo.view.scrollers.HistoryCanvas;
import slogo.view.scrollers.ListViewer;

import java.util.Objects;
import slogo.view.selectors.BackgroundSelector;
import slogo.view.selectors.LanguageSelector;
import slogo.view.selectors.PenSelector;
import slogo.view.selectors.TurtleFaceSelector;

/**
 * This class allows us to make our main class less fat
 * and sets up all the visuals
 * @author Juliet, Natalie (binding)
 */

public class SetupScreen {

  public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";
  public static final double BOX_SPACING = 10;
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 600;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final double BUTTON_HEIGHT_OFFSET = 40;
  public static final double COMMON_COMMAND_BUTTON_HEIGHT_OFFSET = 15;
  public static final double COMMON_COMMAND_BUTTON_WIDTH_OFFSET = 185;
  public static final int COMMAND_COLUMN = 1;
  public static final int LIST_VIEW_COLUMN = 2;
  public static final int ERROR_MESSAGE_PADDING = 250;
  //TODO: hard coded text
  public static final String VARIABLE_TEXT = "Your variables: ";
  public static final String COMMAND_TEXT = "Your new commands: ";
  public static final String COMMON_COMMAND_BUTTON_TEXT = "See Common Commands";
  public static final String GO_BUTTON_TEXT = "Go";
  public static final String CLEAR_BUTTON_TEXT = "Clear Canvas";
  public static final String STOP_BUTTON_TEXT = "Stop Turtle";

  private UserCommandField myUserInput = new UserCommandField(WIDTH, HEIGHT);
  private Group root = new Group();
  private Turtle myTurtle;
  private DrawingCanvas myDrawingCanvas = new DrawingCanvas(WIDTH, HEIGHT);
  private Button myGo;
  private Button myClear;
  private Button myStop;
  private HistoryCanvas myHistory = new HistoryCanvas(COMMAND_COLUMN, DrawingCanvas.CANVAS_TOP_PADDING);
  private ListViewer myNewCommandViewer = new ListViewer(LIST_VIEW_COLUMN, DrawingCanvas.CANVAS_TOP_PADDING, COMMAND_TEXT);
  private ListViewer myVariableView = new ListViewer(LIST_VIEW_COLUMN, HEIGHT/2.0, VARIABLE_TEXT);

  private BackgroundSelector myBackgroundSelector;
  private TurtleFaceSelector myCharacterSelector;
  private PenSelector myPenSelector;
  private LanguageSelector myLanguageSelector;

  private Label myCurrentErrorMessage = new Label();
  private VBox belowInputFieldItems = new VBox(BOX_SPACING);
  private HBox belowCanvasButtons = new HBox(BOX_SPACING);

  /**
   * Sets up all of the visual elements so that
   * the Main class doesn't have to do as much work
   * @return
   */
  public Scene setupGame() {
    //TODO: error handling if image not found
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
    myTurtle = new Turtle(image, myDrawingCanvas.getWidth(), myDrawingCanvas.getHeight());

    setupBox(belowInputFieldItems, UserCommandField.FIELD_SIDE_PADDING*3 + myUserInput.getWidth(), DrawingCanvas.CANVAS_TOP_PADDING + myDrawingCanvas.getHeight() + BOX_SPACING, myDrawingCanvas.getWidth());
    setupBox(belowCanvasButtons, DrawingCanvas.CANVAS_SIDE_PADDING, DrawingCanvas.CANVAS_TOP_PADDING + myDrawingCanvas.getHeight() + BOX_SPACING, myDrawingCanvas.getWidth());
    setButtons();
    setSelectors();

    root.getChildren().addAll(myDrawingCanvas.getView(), myTurtle.getView(), myUserInput.getView(), belowInputFieldItems, belowCanvasButtons, myHistory.getView(), myNewCommandViewer.getView(), myVariableView.getView());
    root.getChildren().addAll(myBackgroundSelector.getView(), myPenSelector.getView(), myCharacterSelector.getView(), myLanguageSelector.getView());

    myCurrentErrorMessage.setLayoutX(myVariableView.getView().getLayoutX());
    myCurrentErrorMessage.setLayoutY(myVariableView.getView().getLayoutY() + ERROR_MESSAGE_PADDING);

    root.getChildren().add(myCurrentErrorMessage);

    return new Scene(root, WIDTH, HEIGHT, BACKGROUND);
  }

  /**
   * Allows us to add a jumper to a common command page
   * @param commonCommands
   */
  public void addCommonCommands(CommonCommands commonCommands) {
    Button commandJumper = new Button(COMMON_COMMAND_BUTTON_TEXT);
    commandJumper.setOnAction(e -> commonCommands.showCommonCommandScene());
    commandJumper.setLayoutX(WIDTH - COMMON_COMMAND_BUTTON_WIDTH_OFFSET);
    commandJumper.setLayoutY(COMMON_COMMAND_BUTTON_HEIGHT_OFFSET);
    root.getChildren().add(commandJumper);
  }

  /**
   * Getter methods necessary to access these elements in the Main class
   * @return
   */
  public Turtle getTurtle() { return myTurtle; }

  public String getUserInput() { return myUserInput.getUserInput(); }

  public DrawingCanvas getDrawingCanvas() { return myDrawingCanvas; }

  public void setVariableList(ObservableList<String> variableList) { myVariableView.bindList(variableList); }

  public void setNewCommandList(ObservableList<String> newCommandList) { myNewCommandViewer.bindList(newCommandList); }

  public void addHistory(String command) { myHistory.addHistory(command);}

  public void bindErrorMessage(StringProperty message) { myCurrentErrorMessage.textProperty().bind(message); }

  public void setGoButton(EventHandler<ActionEvent> goAction) { myGo.setOnAction(goAction); }

  public Group getRoot() { return root; }

  public StringProperty getLanguageChoice() { return myLanguageSelector.getLanguageChoiceProperty(); }

  /**
   * Gives all of the buttons below the canvas their functions
   * @param stopAction
   * @param clearAction
   */
  public void setBelowCanvasButtons(EventHandler<ActionEvent> stopAction, EventHandler<ActionEvent> clearAction) {
    myStop.setOnAction(stopAction);
    myClear.setOnAction(clearAction);
  }

  private void setupBox(Pane box, double x, double y, double width){
    box.setLayoutX(x);
    box.setLayoutY(y);
    box.setMinWidth(width);
  }

  private void setButtons() {
    myGo = new Button(GO_BUTTON_TEXT);
    myGo.setMinWidth(myUserInput.getWidth());
    belowInputFieldItems.getChildren().add(myGo);

    myClear = new Button(CLEAR_BUTTON_TEXT);
    myClear.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    belowCanvasButtons.getChildren().add(myClear);

    myStop = new Button(STOP_BUTTON_TEXT);
    myStop.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    belowCanvasButtons.getChildren().add(myStop);

  }

  private void setSelectors() {
    myBackgroundSelector = new BackgroundSelector(myDrawingCanvas, belowCanvasButtons.getLayoutX(), belowCanvasButtons.getLayoutY()+ BUTTON_HEIGHT_OFFSET);
    myCharacterSelector = new TurtleFaceSelector(myTurtle, myVariableView.getView().getLayoutX(), belowInputFieldItems.getLayoutY() + BUTTON_HEIGHT_OFFSET);
    myPenSelector = new PenSelector(myTurtle, belowInputFieldItems.getLayoutX(), belowInputFieldItems.getLayoutY() + BUTTON_HEIGHT_OFFSET);
    myLanguageSelector = new LanguageSelector(DrawingCanvas.CANVAS_SIDE_PADDING, DrawingCanvas.CANVAS_TOP_PADDING/4);
  }
}

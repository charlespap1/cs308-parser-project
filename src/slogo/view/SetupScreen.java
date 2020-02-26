package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.view.commonCommands.CommonCommands;
import slogo.view.scrollers.HistoryCanvas;
import slogo.view.scrollers.ListViewer;

import java.util.Objects;
import slogo.view.selectors.BackgroundSelector;
import slogo.view.selectors.PenSelector;

/**
 * This class allows us to make our main class less fat
 * and sets up all the visuals
 * @author Juliet
 */

public class SetupScreen {

  public static final String TURTLE_IMAGE = "turtle.png";
  public static final double BOX_SPACING = 10;
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 600;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final double BUTTON_HEIGHT_OFFSET = 40;
  public static final String COMMON_COMMAND_BUTTON_TEXT = "See Common Commands";
  public static final double COMMON_COMMAND_BUTTON_WIDTH_OFFSET = 200;

  private int width;
  private int height;
  private Paint background;

  private UserCommandField myUserInput;
  private Group root;
  private Turtle myTurtle;
  private DrawingCanvas myDrawingCanvas;
  private Button myGo;
  private Button myClear;
  private Button myStop;
  private HistoryCanvas myHistory;
  private ListViewer myNewCommandViewer;
  private ListViewer myVariableView;

  private BackgroundSelector myBackgroundSelector;
  private PenSelector myPenSelector;

  private Label myCurrentErrorMessage = new Label();

  private VBox belowInputFieldItems;
  private HBox belowCanvasButtons;


  public SetupScreen()
  {
    width = WIDTH;
    height = HEIGHT;
    background = BACKGROUND;
    root = new Group();
  }

  /**
   * Sets up all of the visual elements so that
   * the Main class doesn't have to do as much work
   * @return
   */
  public Scene setupGame()
  {
    //TODO: error handling also make settable
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));

    myDrawingCanvas = new DrawingCanvas(width, height);
    myTurtle = new Turtle(image, myDrawingCanvas.getWidth(), myDrawingCanvas.getHeight());
    myUserInput = new UserCommandField(width, height);

    myHistory = new HistoryCanvas(2, DrawingCanvas.CANVAS_TOP_PADDING);
    //TODO: hard coded text
    myVariableView = new ListViewer(2, height/2.0, "Your variables: ");
    myNewCommandViewer = new ListViewer(1, DrawingCanvas.CANVAS_TOP_PADDING, "Your new commands: ");

    setVBoxLayout();
    setHBoxLayout();
    setButtons();
    setSelectors();

    root.getChildren().addAll(myDrawingCanvas.getView(), myTurtle.getView(), myUserInput.getView(), belowInputFieldItems, belowCanvasButtons, myHistory.getView(), myNewCommandViewer.getView(), myVariableView.getView());
    root.getChildren().addAll(myBackgroundSelector.getView(), myPenSelector.getView());
    return new Scene(root, width, height, background);
  }

  private void setHBoxLayout()
  {
    belowCanvasButtons = new HBox(BOX_SPACING);
    belowCanvasButtons.setLayoutX(DrawingCanvas.CANVAS_SIDE_PADDING);
    belowCanvasButtons.setLayoutY(DrawingCanvas.CANVAS_TOP_PADDING + myDrawingCanvas.getHeight() + BOX_SPACING);
    belowCanvasButtons.setMinWidth(myDrawingCanvas.getWidth());
    belowCanvasButtons.setAlignment(Pos.CENTER);
  }

  private void setVBoxLayout()
  {
    belowInputFieldItems = new VBox(BOX_SPACING);
    belowInputFieldItems.setLayoutY(DrawingCanvas.CANVAS_TOP_PADDING + myDrawingCanvas.getHeight() + BOX_SPACING);
    belowInputFieldItems.setLayoutX(UserCommandField.FIELD_SIDE_PADDING*3 + myUserInput.getWidth());
    belowInputFieldItems.setMinWidth(myUserInput.getWidth());
    belowInputFieldItems.setAlignment(Pos.CENTER);
  }

  private void setButtons()
  {
    myGo = new Button("Go");
    myGo.setMinWidth(myUserInput.getWidth());
    belowInputFieldItems.getChildren().add(myGo);
    belowInputFieldItems.getChildren().add(myCurrentErrorMessage);

    //TODO: hard coded text
    myClear = new Button("Clear Canvas");
    myClear.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    belowCanvasButtons.getChildren().add(myClear);

    myStop = new Button("Stop Turtle");
    myStop.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    belowCanvasButtons.getChildren().add(myStop);

  }

  private void setSelectors()
  {
    myBackgroundSelector = new BackgroundSelector(myDrawingCanvas, belowCanvasButtons.getLayoutX(), belowCanvasButtons.getLayoutY() + BUTTON_HEIGHT_OFFSET);
    myPenSelector = new PenSelector(myTurtle, belowInputFieldItems.getLayoutX(), belowInputFieldItems.getLayoutY() + BUTTON_HEIGHT_OFFSET);
  }

  public void addCommonCommands(CommonCommands commonCommands)
  {
    Button commandJumper = new Button(COMMON_COMMAND_BUTTON_TEXT);
    commandJumper.setOnAction(e -> commonCommands.showCommonCommandScene());
    commandJumper.setLayoutX(width - COMMON_COMMAND_BUTTON_WIDTH_OFFSET);
    commandJumper.setLayoutY(BUTTON_HEIGHT_OFFSET);
    root.getChildren().add(commandJumper);
  }


  /**
   * Getter methods necessary to access these elements in the Main class
   * @return
   */
  public Turtle getTurtle()
  {
    return myTurtle;
  }

  public String getUserInput()
  {
    return myUserInput.getUserInput();
  }

  public DrawingCanvas getDrawingCanvas()
  {
    return myDrawingCanvas;
  }

  public void setVariableList(ObservableList<String> variableList) { myVariableView.bindList(variableList); }

  public void setNewCommandList(ObservableList<String> newCommandList) { myNewCommandViewer.bindList(newCommandList); }

  public void addHistory(String command) { myHistory.addHistory(command);}

  public void bindErrorMessage(StringProperty message) { myCurrentErrorMessage.textProperty().bind(message); }

  public void setGoButton(EventHandler<ActionEvent> goAction)
  {
    myGo.setOnAction(goAction);
  }

  public void setBelowCanvasButtons(EventHandler<ActionEvent> stopAction, EventHandler<ActionEvent> clearAction) {
    myStop.setOnAction(stopAction);
    myClear.setOnAction(clearAction);
  }

  public Group getRoot()
  {
    return root;
  }

}

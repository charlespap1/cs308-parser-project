package slogo.view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import slogo.Main;

/**
 * This class allows us to make our main class less fat
 * and sets up all the visuals
 * @author Juliet
 */

public class SetupScreen {

  public static final String TURTLE_IMAGE = "turtle.png";
  private static final double BOX_SPACING = Main.BOX_SPACING;

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
  private CommonCommands myCommonCommands;
  private VariableViewer myVariableView;

  private Text myCurrentErrorMessage;

  private VBox belowInputFieldItems;
  private HBox belowCanvasButtons;


  public SetupScreen(int width, int height, Paint background, Group root)
  {
    this.width = width;
    this.height = height;
    this.background = background;
    this.root = root;
  }

  /**
   * Sets up all of the visual elements so that
   * the Main class doesn't have to do as much work
   * @return
   */
  public Scene setupGame()
  {
    Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE));

    myCurrentErrorMessage = new Text("");
    myDrawingCanvas = new DrawingCanvas(width, height);
    myTurtle = new Turtle(image, myDrawingCanvas.getWidth(), myDrawingCanvas.getHeight());
    myUserInput = new UserCommandField(width, height);

    myHistory = new HistoryCanvas(2, DrawingCanvas.CANVAS_TOP_PADDING);
    myVariableView = new VariableViewer(2, height/2);
    myCommonCommands = new CommonCommands(1, DrawingCanvas.CANVAS_TOP_PADDING);

    setVBoxLayout();
    setHBoxLayout();
    setButtons();

    root.getChildren().addAll(myDrawingCanvas.getView(), myTurtle.getView(), myUserInput.getView(), belowInputFieldItems, belowCanvasButtons, myHistory.getView(), myCommonCommands.getView(), myVariableView.getView());

    Scene scene = new Scene(root, width, height, background);
    return scene;

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
    myGo = new Button(GoButton.BUTTON_TEXT);
    myGo.setMinWidth(myUserInput.getWidth());
    //myGo.setOnAction(e -> getInstruction());
    belowInputFieldItems.getChildren().add(myGo);
    belowInputFieldItems.getChildren().add(myCurrentErrorMessage);

    myClear = new Button("Clear Canvas");
    myClear.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    //myClear.setOnAction(e -> clearCanvas());
    belowCanvasButtons.getChildren().add(myClear);

    myStop = new Button("Stop Turtle");
    myStop.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    //myStop.setOnAction(e -> returnTurtle());
    belowCanvasButtons.getChildren().add(myStop);
  }

  /**
   * Getter methods necessary to access these elements in the Main class
   * @return
   */
  public Button getGoButton()
  {

    return myGo;
  }
  public Button getStopButton()
  {

    return myStop;
  }
  public Button getClearButton()
  {

    return myClear;
  }
  public Turtle getTurtle()
  {

    return myTurtle;
  }

  public UserCommandField getUserInput()
  {
    return myUserInput;
  }
  public DrawingCanvas getDrawingCanvas()
  {

    return myDrawingCanvas;
  }

  public HistoryCanvas getHistoryCanvas()
  {

    return myHistory;
  }

  public Text getCurrentErrorMessage()
  {

    return myCurrentErrorMessage;
  }

  public Group getRoot()
  {
    return root;
  }


}

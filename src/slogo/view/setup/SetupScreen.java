package slogo.view.setup;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import slogo.controller.DirectExecutor;
import slogo.model.tokens.Token;
import slogo.view.DrawingCanvas;
import slogo.view.LanguageHelper;
import slogo.view.LineManager;
import slogo.view.Turtle;
import slogo.view.TurtleGraphicalMover;
import slogo.view.UserCommandField;
import slogo.view.commonCommands.CommonCommands;
import slogo.view.popup.FileDoesNotExistException;
import slogo.view.popup.LoadConfigPopup;

import slogo.view.popup.SetPreferencesPopup;

import slogo.view.popup.TurtleStatePopup;
import slogo.view.scrollers.CommandViewer;
import slogo.view.scrollers.HistoryViewer;
import slogo.view.scrollers.ScrollingWindow;
import slogo.view.scrollers.VariableViewer;
import slogo.view.selectors.DisplayCustomizer;
import slogo.view.selectors.LanguageSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class allows us to make our main class less fat
 * and sets up all the visuals
 * @author Juliet, Natalie
 */

public class SetupScreen {
  public static final String MAIN_STYLESHEET = "main.css";

  public static final double BOX_SPACING = 10;
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 720;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final double BUTTON_HEIGHT_OFFSET = 40;
  public static final double GRAPHICAL_VIEWER_HEIGHT_OFFSET = 255;
  public static final double COMMON_COMMAND_BUTTON_HEIGHT_OFFSET = 15;
  public static final double COMMON_COMMAND_BUTTON_WIDTH_OFFSET = 225;
  public static final int COMMAND_COLUMN = 1;
  public static final int LIST_VIEW_COLUMN = 2;
  public static final int ERROR_MESSAGE_PADDING = 320;
  public static final double HALFWAY_DOWN = HEIGHT/2.0;

  public static final String VARIABLE_TITLE_KEY = "VariableTitleText";
  public static final String HISTORY_TITLE_KEY = "HistoryTitleText";
  public static final String NEW_COMMAND_TITLE_KEY = "NewCommandTitleText";

  private static final String BACKGROUND_SELECTOR_TEXT_KEY = "BackgroundSelectorText";
  private static final String LANGUAGE_SELECTOR_TEXT_KEY = "LanguageSelectorText";
  private static final String PEN_SELECTOR_TEXT_KEY = "PenSelectorText";
  private static final String PEN_THICKNESS_TEXT_KEY = "PenThicknessText";
  private static final String TURTLE_SELECTOR_TEXT_KEY = "TurtleSelectorText";

  public static final String COMMON_COMMAND_BUTTON_KEY = "CommonCommandButton";
  public static final String GO_BUTTON_KEY = "GoButton";
  public static final String CLEAR_BUTTON_KEY = "ClearButton";
  public static final String STOP_BUTTON_KEY = "StopButton";
  public static final String NEW_WINDOW_BUTTON_KEY = "NewWindowButton";
  public static final String PEN_UP_BUTTON_KEY = "PenUpButton";
  public static final String PEN_DOWN_BUTTON_KEY = "PenDownButton";
  private static final String NEW_CONFIG_BUTTON_KEY = "NewConfigButton";
  private static final String UNDO_BUTTON_KEY = "UndoButton";
  private static final String REDO_BUTTON_KEY = "RedoButton";

  private static final String LOAD_FILE_PROMPT = "LoadFilePrompt";
  private static final String SELECT_PREFERENCES_PROMPT = "SelectPreferencesPrompt";

  private UserCommandField myUserInput = new UserCommandField(WIDTH, HEIGHT);
  private Group root = new Group();
  private List<Turtle> myTurtles = new ArrayList<>();
  private DrawingCanvas myDrawingCanvas = new DrawingCanvas(WIDTH, HEIGHT);
  private Button myGo;
  private Button myClear;
  private Button myStop;
  private Button myNewWindow;

  private Button loadFileButton;

  private LoadConfigPopup myCurrentLoadPopup;
  private SetPreferencesPopup myCurrentNewWindowPopup;

  private Button undoButton;
  private Button redoButton;

  private HistoryViewer myHistory = new HistoryViewer(COMMAND_COLUMN, DrawingCanvas.CANVAS_TOP_PADDING);
  private ScrollingWindow myNewCommandViewer = new CommandViewer(LIST_VIEW_COLUMN, DrawingCanvas.CANVAS_TOP_PADDING, this::setInputText);
  private ScrollingWindow myVariableView = new VariableViewer(LIST_VIEW_COLUMN, HALFWAY_DOWN);
  private LineManager myLineManager = new LineManager(root);
  //~~~~~~~~~~~~~ vvv for testing and troubleshooting vvv ~~~~~~~~~~~~~~~~
  private Button myTestButton;
  private Button myTurtlesStatesButton;
  private TurtleStatePopup myTurtleStatePopup;
  //~~~~~~~~~~~~~ ^^^ for testing and troubleshooting ^^^ ~~~~~~~~~~~~~~~~


  private LanguageSelector myLanguageSelector;

  private Label myCurrentErrorMessage = new Label();
  private VBox belowInputFieldItems = new VBox(BOX_SPACING);
  private HBox belowCanvasButtons = new HBox(BOX_SPACING);

  private DisplayCustomizer myCustomizer;

  private LanguageHelper languageHelper;
  private TurtleGraphicalMover myGraphicalMover;

  /**
   * Sets up all of the visual elements so that
   * the Main class doesn't have to do as much work
   * @return
   */
  public Scene setupGame() {
    setupBox(belowInputFieldItems, UserCommandField.FIELD_SIDE_PADDING*3 + myUserInput.getWidth(), DrawingCanvas.CANVAS_TOP_PADDING + myDrawingCanvas.getHeight() + BOX_SPACING*5, myDrawingCanvas.getWidth());
    setupBox(belowCanvasButtons, DrawingCanvas.CANVAS_SIDE_PADDING, DrawingCanvas.CANVAS_TOP_PADDING + myDrawingCanvas.getHeight() + BOX_SPACING, myDrawingCanvas.getWidth());
    setButtons();
    setSelectors();

    myGraphicalMover = new TurtleGraphicalMover(myUserInput.getView().getLayoutX(), myUserInput.getView().getLayoutY() + GRAPHICAL_VIEWER_HEIGHT_OFFSET);
    myCustomizer = new DisplayCustomizer(belowCanvasButtons.getLayoutX(), belowCanvasButtons.getLayoutY()+ BUTTON_HEIGHT_OFFSET + 10);
    setText();

    root.getChildren().addAll(myDrawingCanvas.getView(), myUserInput.getView(), belowInputFieldItems, belowCanvasButtons, myHistory.getView(), myNewCommandViewer.getView(), myVariableView.getView());
    root.getChildren().addAll(myGraphicalMover.getView(), myCustomizer.getView(), myLanguageSelector.getView());

    myCurrentErrorMessage.setLayoutX(myVariableView.getView().getLayoutX());
    myCurrentErrorMessage.setLayoutY(myVariableView.getView().getLayoutY() + ERROR_MESSAGE_PADDING);

    root.getChildren().add(myCurrentErrorMessage);

    Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(MAIN_STYLESHEET)).toExternalForm());

    return scene;
  }

  public void setDirectExecutor(DirectExecutor executor){
    myGraphicalMover.setCommandNameProperties(languageHelper.getStringProperty("Forward"), languageHelper.getStringProperty("Right"),
            languageHelper.getStringProperty("Back"), languageHelper.getStringProperty("Left"));
    myGraphicalMover.setButtons(executor, myLineManager);
    myHistory.setDirectExecutor(executor, myLineManager);
  }


  /**
   * Allows us to add a jumper to a common command page
   * @param
   */
  public void addCommonCommands(Stage primaryStage, Scene scene) {
    CommonCommands commonCommands = new CommonCommands(primaryStage, scene, myLanguageSelector.getLanguageChoiceProperty());
    Button myCommandJumper = new Button();
    myCommandJumper.textProperty().bind(languageHelper.getStringProperty(COMMON_COMMAND_BUTTON_KEY));
    myCommandJumper.setOnAction(e -> commonCommands.showCommonCommandScene());
    myCommandJumper.setLayoutX(WIDTH - COMMON_COMMAND_BUTTON_WIDTH_OFFSET);
    myCommandJumper.setLayoutY(COMMON_COMMAND_BUTTON_HEIGHT_OFFSET);
    root.getChildren().add(myCommandJumper);
  }

  public void bindErrorMessage(StringProperty message) {
    myCurrentErrorMessage.textProperty().bindBidirectional(message);
    myCurrentErrorMessage.setTextFill(Color.RED);
  }

  public void setGoButton(EventHandler<ActionEvent> goAction) { myGo.setOnAction(goAction); }

  public void setNewWindowButton(EventHandler<ActionEvent> newWindowAction, Stage stage) {

    EventHandler<ActionEvent> e = event -> {
      myCurrentNewWindowPopup = new SetPreferencesPopup();
      myCurrentNewWindowPopup.setPromptProperty(languageHelper.getStringProperty(SELECT_PREFERENCES_PROMPT));
      myCurrentNewWindowPopup.setGoButtonProperty(languageHelper.getStringProperty(GO_BUTTON_KEY));
      myCurrentNewWindowPopup.getMyPopup().show(stage);
      myCurrentNewWindowPopup.setPopupButton(newWindowAction);
    };

    myNewWindow.setOnAction(e);

  }


  public File getFile() {
    myLineManager.newProgram();
    try{
      return myCurrentLoadPopup.getFile();
    }
    catch(FileDoesNotExistException err) {
      myCurrentErrorMessage.textProperty().setValue(err.getMessage());
      return null;
    }
  }


  public void setLoadTextFileButton(EventHandler<ActionEvent> loadFileAction, Stage primaryStage) {
    EventHandler<ActionEvent> e = event -> {
      myCurrentLoadPopup = new LoadConfigPopup();
      myCurrentLoadPopup.setPromptProperty(languageHelper.getStringProperty(LOAD_FILE_PROMPT));
      myCurrentLoadPopup.setGoButtonProperty(languageHelper.getStringProperty(GO_BUTTON_KEY));
      myCurrentLoadPopup.getMyPopup().show(primaryStage);
      myCurrentLoadPopup.setPopupButton(loadFileAction);
    };
    loadFileButton.addEventHandler(ActionEvent.ACTION, e);
  }

  public void setInputText(String command) { myUserInput.setUserInput(command); }
  public void setVariableList(ObservableList<Token> variableList) { myVariableView.bindList(variableList); }
  public void setNewCommandList(ObservableList<Token> newCommandList) { myNewCommandViewer.bindList(newCommandList); }
  public void setupHistory(ObservableList<Token> historyList, BooleanProperty undoDisabled, BooleanProperty redoDisabled) {
    myHistory.bindList(historyList);
    undoButton.disableProperty().bind(undoDisabled);
    redoButton.disableProperty().bind(redoDisabled);
  }
  public void setClearHistory(EventHandler<ActionEvent> clearAction) {
    myClear.setOnAction(e -> {
      myLineManager.clearAllLines();
      moveTurtlesToCenter();
      clearAction.handle(e);
    });
  }

  public ScreenManager getScreenManager(){
    return new ScreenManager(root, myUserInput, myTurtles, myDrawingCanvas, myLanguageSelector, myLineManager, myCustomizer, myGraphicalMover);
  }

  private void setupBox(Pane box, double x, double y, double width){
    box.setLayoutX(x);
    box.setLayoutY(y);
    box.setMinWidth(width);
  }

  private void moveTurtlesToCenter(){
    boolean tempPen = myGraphicalMover.getPenUp();
    myGraphicalMover.setPenUp(true);
    for(Turtle t : myTurtles) {
      t.returnTurtleToDefault();
    }
    myGraphicalMover.setPenUp(tempPen);
  }

  private void setButtons() {
    myGo = new Button();
    myGo.setMinWidth(myUserInput.getWidth());
    belowInputFieldItems.getChildren().add(myGo);
    myClear = new Button();
    belowCanvasButtons.getChildren().add(myClear);
    myStop = new Button();
    belowCanvasButtons.getChildren().add(myStop);

    myStop.setOnAction(e -> moveTurtlesToCenter());

    //~~~~~~~~~~~~~ vvv for testing and troubleshooting vvv ~~~~~~~~~~~~~~~~
    myTestButton = new Button();
    myTestButton.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    myTestButton.setText("Test");
    myTestButton.setOnAction(e -> {
//      myTurtlePopUpWindow.addTurtle(myTurtles.get(0));
//      myTurtlePopUpWindow.printTurtles();
    });
    //belowCanvasButtons.getChildren().add(myTestButton);

    myTurtlesStatesButton = new Button();
    myTurtlesStatesButton.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    myTurtlesStatesButton.setText("States");
    //belowCanvasButtons.getChildren().add(myTurtlesStatesButton);
    //~~~~~~~~~~~~~ ^^^ for testing and troubleshooting ^^^ ~~~~~~~~~~~~~~~~

    loadFileButton = new Button();
    myNewWindow = new Button();
    HBox newWindowButtons = new HBox(BOX_SPACING);
    newWindowButtons.setLayoutY(COMMON_COMMAND_BUTTON_HEIGHT_OFFSET);
    newWindowButtons.setLayoutX(WIDTH/2 - BUTTON_HEIGHT_OFFSET*3);
    newWindowButtons.getChildren().addAll(myNewWindow, loadFileButton);
    undoButton = new Button();
    //undoButton.setText("Undo");
    redoButton = new Button();
    //redoButton.setText("Redo");
    belowCanvasButtons.setMaxWidth(myDrawingCanvas.getWidth());
    belowCanvasButtons.setMinWidth(myDrawingCanvas.getWidth());
    belowCanvasButtons.setAlignment(Pos.CENTER);
    belowCanvasButtons.getChildren().addAll(undoButton, redoButton);

    root.getChildren().add(newWindowButtons);
  }

  public void setUndoButton (EventHandler<ActionEvent> undoAction) {
    undoButton.setOnAction(event -> {
      boolean tempPen = myGraphicalMover.getPenUp();
      myGraphicalMover.setPenUp(true);
      myLineManager.undo();
      undoAction.handle(event);
      myGraphicalMover.setPenUp(tempPen);
    });
  }

  public void setRedoButton (EventHandler<ActionEvent> redoAction) {
    redoButton.setOnAction(event -> {
      boolean tempPen = myGraphicalMover.getPenUp();
      myGraphicalMover.setPenUp(true);
      myLineManager.redo();
      redoAction.handle(event);
      myGraphicalMover.setPenUp(tempPen);
    });
  }
  //~~~~~~~~~~~~~ vvv for testing and troubleshooting vvv ~~~~~~~~~~~~~~~~
  public void setTurtlesStatesButton (EventHandler<ActionEvent> showTurtlesAction) {
    myTurtlesStatesButton.setOnAction(showTurtlesAction);
  };
  //~~~~~~~~~~~~~ ^^^ for testing and troubleshooting ^^^ ~~~~~~~~~~~~~~~~


  private void setSelectors() {
    myLanguageSelector = new LanguageSelector(DrawingCanvas.CANVAS_SIDE_PADDING, DrawingCanvas.CANVAS_TOP_PADDING/4);
  }

  private void setText() {
    languageHelper = new LanguageHelper(myLanguageSelector.getLanguageChoiceProperty());
    myGo.textProperty().bind(languageHelper.getStringProperty(GO_BUTTON_KEY));
    myClear.textProperty().bind(languageHelper.getStringProperty(CLEAR_BUTTON_KEY));
    myStop.textProperty().bind(languageHelper.getStringProperty(STOP_BUTTON_KEY));
    myNewWindow.textProperty().bind(languageHelper.getStringProperty(NEW_WINDOW_BUTTON_KEY));
    loadFileButton.textProperty().bind(languageHelper.getStringProperty(NEW_CONFIG_BUTTON_KEY));
    undoButton.textProperty().bind(languageHelper.getStringProperty(UNDO_BUTTON_KEY));
    redoButton.textProperty().bind(languageHelper.getStringProperty(REDO_BUTTON_KEY));

    myVariableView.setTitleProperty(languageHelper.getStringProperty(VARIABLE_TITLE_KEY));
    myNewCommandViewer.setTitleProperty(languageHelper.getStringProperty(NEW_COMMAND_TITLE_KEY));
    myHistory.setTitleProperty(languageHelper.getStringProperty(HISTORY_TITLE_KEY));

    myCustomizer.setTitleProperty(languageHelper.getStringProperty(BACKGROUND_SELECTOR_TEXT_KEY), languageHelper.getStringProperty(PEN_SELECTOR_TEXT_KEY),languageHelper.getStringProperty(TURTLE_SELECTOR_TEXT_KEY));

    myLanguageSelector.setTitleProperty(languageHelper.getStringProperty(LANGUAGE_SELECTOR_TEXT_KEY));
    myGraphicalMover.setTitleProperty(languageHelper.getStringProperty(PEN_THICKNESS_TEXT_KEY));
    myGraphicalMover.setPenLabelProperty(languageHelper.getStringProperty(PEN_UP_BUTTON_KEY), languageHelper.getStringProperty(PEN_DOWN_BUTTON_KEY));
  }

  public TurtleStatePopup getTurtleStatePopup() {
    return myTurtleStatePopup;
  }

  public String getNewWindowPreferences(){
    return myCurrentNewWindowPopup.getPreference();
  }
}
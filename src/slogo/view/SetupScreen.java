package slogo.view;

import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.shape.Line;
import slogo.model.code.Token;
import slogo.view.commonCommands.CommonCommands;
import slogo.view.scrollers.CommandViewer;
import slogo.view.scrollers.HistoryCanvas;
import slogo.view.scrollers.ListViewer;

import java.util.Objects;

import slogo.view.scrollers.VariableViewer;
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
  public static final String MAIN_STYLESHEET = "main.css";

  public static final double BOX_SPACING = 10;
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 720;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final double BUTTON_HEIGHT_OFFSET = 40;
  public static final double GRAPHICAL_VIEWER_HEIGHT_OFFSET = 35;
  public static final double CHARACTER_TYPE_OFFSET = 100;
  public static final double COMMON_COMMAND_BUTTON_HEIGHT_OFFSET = 15;
  public static final double COMMON_COMMAND_BUTTON_WIDTH_OFFSET = 225;
  public static final int COMMAND_COLUMN = 1;
  public static final int LIST_VIEW_COLUMN = 2;
  public static final int ERROR_MESSAGE_PADDING = 285;
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


  private UserCommandField myUserInput = new UserCommandField(WIDTH, HEIGHT);
  private Group root = new Group();
  private List<Turtle> myTurtles = new ArrayList<>();
  private DrawingCanvas myDrawingCanvas = new DrawingCanvas(WIDTH, HEIGHT);
  private Button myGo;
  private Button myClear;
  private Button myStop;
  private Button myCommandJumper;
  private Button myNewWindow;
  private Button myNewConfig;

  private Button undoButton;
  private Button redoButton;

  private HistoryCanvas myHistory = new HistoryCanvas(COMMAND_COLUMN, DrawingCanvas.CANVAS_TOP_PADDING);
  private ListViewer myNewCommandViewer = new CommandViewer(LIST_VIEW_COLUMN, DrawingCanvas.CANVAS_TOP_PADDING, this::setInputText);
  private ListViewer myVariableView = new VariableViewer(LIST_VIEW_COLUMN, HEIGHT/2.0);
  //~~~~~~~~~~~~~ vvv for testing and troubleshooting vvv ~~~~~~~~~~~~~~~~
  private Button myTestButton;
  //~~~~~~~~~~~~~ ^^^ for testing and troubleshooting ^^^ ~~~~~~~~~~~~~~~~

  private BackgroundSelector myBackgroundSelector;
  private TurtleFaceSelector myCharacterSelector;
  private PenSelector myPenSelector;
  private LanguageSelector myLanguageSelector;

  private Label myCurrentErrorMessage = new Label();
  private VBox belowInputFieldItems = new VBox(BOX_SPACING);
  private HBox belowCanvasButtons = new HBox(BOX_SPACING);

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

    myGraphicalMover = new TurtleGraphicalMover(myBackgroundSelector.getView().getLayoutX(), myBackgroundSelector.getView().getLayoutY() + GRAPHICAL_VIEWER_HEIGHT_OFFSET);

    setText();

    root.getChildren().addAll(myDrawingCanvas.getView(), myUserInput.getView(), belowInputFieldItems, belowCanvasButtons, myHistory.getView(), myNewCommandViewer.getView(), myVariableView.getView());
    root.getChildren().addAll(myBackgroundSelector.getView(), myPenSelector.getView(), myCharacterSelector.getView(), myLanguageSelector.getView(), myGraphicalMover.getView());

    myCurrentErrorMessage.setLayoutX(myHistory.getView().getLayoutX());
    myCurrentErrorMessage.setLayoutY(myVariableView.getView().getLayoutY() + ERROR_MESSAGE_PADDING);

    root.getChildren().add(myCurrentErrorMessage);

    //setPreferences();

    Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(MAIN_STYLESHEET)).toExternalForm());

    return scene;
  }

  /**
   * Allows us to add a jumper to a common command page
   * @param commonCommands
   */
  public void addCommonCommands(CommonCommands commonCommands) {
    myCommandJumper = new Button();
    myCommandJumper.textProperty().bind(languageHelper.getStringProperty(COMMON_COMMAND_BUTTON_KEY));
    myCommandJumper.setOnAction(e -> commonCommands.showCommonCommandScene());
    myCommandJumper.setLayoutX(WIDTH - COMMON_COMMAND_BUTTON_WIDTH_OFFSET);
    myCommandJumper.setLayoutY(COMMON_COMMAND_BUTTON_HEIGHT_OFFSET);
    root.getChildren().add(myCommandJumper);
  }

  public void addNewTurtle(slogo.model.Turtle turtle) {
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
    Turtle newTurtle = new Turtle(image, myDrawingCanvas.getWidth(), myDrawingCanvas.getHeight());
    myTurtles.add(newTurtle);
    root.getChildren().add(newTurtle.getView());
    newTurtle.setProperties(turtle);
    turtle.pointProperty().addListener((o, oldVal, newVal) -> update(newTurtle));
    turtle.currCommandProperty().addListener((o, oldVal, newVal) -> addHistory(newVal));
  }

  public void setInputText(String command) { myUserInput.setUserInput(command); }


  public String getUserInput() { return myUserInput.getUserInput(); }

  public DrawingCanvas getDrawingCanvas() { return myDrawingCanvas; }

  public void setVariableList(ObservableList<Token> variableList) { myVariableView.bindList(variableList); }

  public void setNewCommandList(ObservableList<Token> newCommandList) { myNewCommandViewer.bindList(newCommandList); }

  public void addHistory(String command) { myHistory.addHistory(command);}

  public void bindErrorMessage(StringProperty message) {
    myCurrentErrorMessage.textProperty().bind(message);
    myCurrentErrorMessage.setTextFill(Color.RED);
  }

  public void setGoButton(EventHandler<ActionEvent> goAction) { myGo.setOnAction(goAction); }

  public void setNewWindowButton(EventHandler<ActionEvent> newWindowAction) { myNewWindow.setOnAction(newWindowAction);}
  public void setNewConfigButton(EventHandler<ActionEvent> newConfigAction) { myNewConfig.setOnAction(newConfigAction); }

  public Group getRoot() { return root; }

  public StringProperty getLanguageChoice() { return myLanguageSelector.getLanguageChoiceProperty(); }

  public int setPenColor(List<Double> params) {
    int index = params.get(0).intValue();
    //TODO: implement with palette

    for (Turtle turtle : myTurtles) turtle.changePenColor(Color.RED);
    return index;
  }

  public int setBackground(List<Double> params){
    int index = params.get(0).intValue();
    //TODO: implement with palette
    myDrawingCanvas.changeBackground(Color.RED);
    return index;
  }

  public int setPenThickness(List<Double> params){
    int index = params.get(0).intValue();
    //TODO: implement with palette ? unsure
    for (Turtle turtle : myTurtles) turtle.setThickness(index);
    return index;
  }

  public int setTurtleImage(List<Double> params){
    int index = params.get(0).intValue();
    //TODO: implement with palette
    for (Turtle turtle : myTurtles) turtle.changeImage(null);
    return index;
  }

  public int setPalette(List<Double> params){return 0;}

  public int getPenColor(List<Double> params) { return 0;}
  public int getShape(List<Double> params) { return 0; }

  public int clearScreen(List<Double> params) {
    myHistory.clearHistory();
    for(Turtle t: myTurtles)
    {
      t.returnTurtleToDefault();
    }
    root.getChildren().removeAll(myDrawingCanvas.getLines());
    return 0;
  }

  /**
   * Updates the movement of the turtle according to new states
   */
  private void update(Turtle newTurtle) {
    Line newLine = newTurtle.drawLineAndBound();
    if (newLine!=null) {
      root.getChildren().add(newLine);
      myDrawingCanvas.addLine(newLine);
      newTurtle.getView().toFront();
    }
  }

  private void setupBox(Pane box, double x, double y, double width){
    box.setLayoutX(x);
    box.setLayoutY(y);
    box.setMinWidth(width);
  }

  private void setButtons() {
    myGo = new Button();
    myGo.setMinWidth(myUserInput.getWidth());
    belowInputFieldItems.getChildren().add(myGo);

    myClear = new Button();
    myClear.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    belowCanvasButtons.getChildren().add(myClear);
    myClear.setOnAction(e -> clearScreen(null));

    myStop = new Button();
    myStop.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    belowCanvasButtons.getChildren().add(myStop);
    myStop.setOnAction(e -> {
      for(Turtle t : myTurtles) t.returnTurtleToDefault();
      clearScreen(null);
    });
    //~~~~~~~~~~~~~ vvv for testing and troubleshooting vvv ~~~~~~~~~~~~~~~~
    myTestButton = new Button();
    myTestButton.setMinWidth(myDrawingCanvas.getWidth()/2 - BOX_SPACING);
    myTestButton.setText("Test");
    myTestButton.setOnAction(e -> {
//      myTurtlePopUpWindow.addTurtle(myTurtles.get(0));
//      myTurtlePopUpWindow.printTurtles();
    });
    belowCanvasButtons.getChildren().add(myTestButton);
    //~~~~~~~~~~~~~ ^^^ for testing and troubleshooting ^^^ ~~~~~~~~~~~~~~~~

    HBox newWindowButtons = new HBox(BOX_SPACING);
    myNewConfig = new Button();

    myNewWindow = new Button();
    newWindowButtons.setLayoutY(COMMON_COMMAND_BUTTON_HEIGHT_OFFSET);
    newWindowButtons.setLayoutX(WIDTH/2 - BUTTON_HEIGHT_OFFSET*3);
    newWindowButtons.getChildren().addAll(myNewWindow, myNewConfig);

    undoButton = new Button();
    undoButton.setText("Undo");
    redoButton = new Button();
    redoButton.setText("Redo");
    belowCanvasButtons.getChildren().addAll(undoButton, redoButton);

    root.getChildren().add(newWindowButtons);

  }

  public void setUndoButton (EventHandler<ActionEvent> undoAction) {
    undoButton.setOnAction(undoAction);
  }

  public void setRedoButton (EventHandler<ActionEvent> redoAction) {
    redoButton.setOnAction(redoAction);
  }

  private void setSelectors() {
    myBackgroundSelector = new BackgroundSelector(myDrawingCanvas, belowCanvasButtons.getLayoutX(), belowCanvasButtons.getLayoutY()+ BUTTON_HEIGHT_OFFSET);
    myCharacterSelector = new TurtleFaceSelector(myVariableView.getView().getLayoutX(), belowInputFieldItems.getLayoutY() + CHARACTER_TYPE_OFFSET);
    myPenSelector = new PenSelector(belowInputFieldItems.getLayoutX(), belowInputFieldItems.getLayoutY() + BUTTON_HEIGHT_OFFSET);
    myLanguageSelector = new LanguageSelector(DrawingCanvas.CANVAS_SIDE_PADDING, DrawingCanvas.CANVAS_TOP_PADDING/4);
  }

  private void setText() {
    languageHelper = new LanguageHelper(myLanguageSelector.getLanguageChoiceProperty());
    myGo.textProperty().bind(languageHelper.getStringProperty(GO_BUTTON_KEY));
    myClear.textProperty().bind(languageHelper.getStringProperty(CLEAR_BUTTON_KEY));
    myStop.textProperty().bind(languageHelper.getStringProperty(STOP_BUTTON_KEY));
    myNewWindow.textProperty().bind(languageHelper.getStringProperty(NEW_WINDOW_BUTTON_KEY));
    myNewConfig.textProperty().bind(languageHelper.getStringProperty(NEW_CONFIG_BUTTON_KEY));

    myVariableView.setTitleProperty(languageHelper.getStringProperty(VARIABLE_TITLE_KEY));
    myNewCommandViewer.setTitleProperty(languageHelper.getStringProperty(NEW_COMMAND_TITLE_KEY));
    myHistory.setTitleProperty(languageHelper.getStringProperty(HISTORY_TITLE_KEY));

    myBackgroundSelector.setTitleProperty(languageHelper.getStringProperty(BACKGROUND_SELECTOR_TEXT_KEY));
    myPenSelector.setTitleProperty(languageHelper.getStringProperty(PEN_SELECTOR_TEXT_KEY));
    myCharacterSelector.setTitleProperty(languageHelper.getStringProperty(TURTLE_SELECTOR_TEXT_KEY));

    myLanguageSelector.setTitleProperty(languageHelper.getStringProperty(LANGUAGE_SELECTOR_TEXT_KEY));
    myGraphicalMover.setTitleProperty(languageHelper.getStringProperty(PEN_THICKNESS_TEXT_KEY));
    myGraphicalMover.setPenLabelProperty(languageHelper.getStringProperty(PEN_UP_BUTTON_KEY), languageHelper.getStringProperty(PEN_DOWN_BUTTON_KEY));

  }

  public void setPreferences(String preferenceTitle)
  {
    PreferenceLoaderSelector myPreferences = new PreferenceLoaderSelector(preferenceTitle);

    for(Turtle t : myTurtles)
    {
      myPreferences.setTurtle(t);
    }
    myPreferences.changeBackground(myDrawingCanvas);
  }

}

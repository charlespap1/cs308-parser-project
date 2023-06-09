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
import slogo.view.*;
import slogo.view.commonCommands.CommonCommands;
import slogo.view.exceptions.FileDoesNotExistException;
import slogo.view.exceptions.TextParsingException;
import slogo.view.exceptions.WriterCannotFindFileException;
import slogo.view.popup.LoadConfigPopup;
import slogo.view.popup.SetPreferencesPopup;
import slogo.view.scrollers.CommandViewer;
import slogo.view.scrollers.HistoryViewer;
import slogo.view.scrollers.ScrollingWindow;
import slogo.view.scrollers.VariableViewer;
import slogo.view.selectors.DisplayCustomizer;
import slogo.view.selectors.LanguageSelector;

import java.io.*;
import java.util.*;

/**
 * This class allows us to make our main class less fat
 * and sets most of the visuals
 * EDIT: Now Setup has been broken up into SetupScreen, Screen Manager and PreferenceLoaderSelector
 * in order to democratize all of the work.
 * SetupScreen specifically holds all the buttons which need to access methods and
 * objects in the controller
 *
 * @author Juliet, Natalie
 */

public class SetupScreen {
    private static final String MAIN_STYLESHEET = "main.css";
    private static final String FILE_CASE_PREFERENCE = "UTF-8";

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 720;
    private static final double BOX_SPACING = 10;
    private static final Paint BACKGROUND = Color.AZURE;
    private static final double BUTTON_HEIGHT_OFFSET = 40;
    private static final double GRAPHICAL_VIEWER_HEIGHT_OFFSET = 255;
    private static final double COMMON_COMMAND_BUTTON_HEIGHT_OFFSET = 15;
    private static final double COMMON_COMMAND_BUTTON_WIDTH_OFFSET = 225;
    private static final int COMMAND_COLUMN = 1;
    private static final int LIST_VIEW_COLUMN = 2;
    private static final int ERROR_MESSAGE_PADDING = 300;
    private static final double HALFWAY_DOWN = HEIGHT / 2.0;
    private static final double SAVE_VARS_PADDING_Y = 322;

    private static final String VARIABLE_TITLE_KEY = "VariableTitleText";
    private static final String HISTORY_TITLE_KEY = "HistoryTitleText";
    private static final String NEW_COMMAND_TITLE_KEY = "NewCommandTitleText";
    private static final String BACKGROUND_SELECTOR_TEXT_KEY = "BackgroundSelectorText";
    private static final String LANGUAGE_SELECTOR_TEXT_KEY = "LanguageSelectorText";
    private static final String PEN_SELECTOR_TEXT_KEY = "PenSelectorText";
    private static final String PEN_THICKNESS_TEXT_KEY = "PenThicknessText";
    private static final String TURTLE_SELECTOR_TEXT_KEY = "TurtleSelectorText";
    private static final String COMMON_COMMAND_BUTTON_KEY = "CommonCommandButton";
    private static final String GO_BUTTON_KEY = "GoButton";
    private static final String CLEAR_BUTTON_KEY = "ClearButton";
    private static final String STOP_BUTTON_KEY = "StopButton";
    private static final String NEW_WINDOW_BUTTON_KEY = "NewWindowButton";
    private static final String PEN_UP_BUTTON_KEY = "PenUpButton";
    private static final String PEN_DOWN_BUTTON_KEY = "PenDownButton";
    private static final String NEW_CONFIG_BUTTON_KEY = "NewConfigButton";
    private static final String UNDO_BUTTON_KEY = "UndoButton";
    private static final String REDO_BUTTON_KEY = "RedoButton";
    private static final String SAVE_BUTTON_KEY = "SaveButton";
    private static final String SAVE_VARS_BUTTON_KEY = "SaveVarsButton";
    private static final String LOAD_VARS_BUTTON_KEY = "LoadVarsButton";
    private static final String FORWARD_KEY = "Forward";
    private static final String BACKWARD_KEY = "Backward";
    private static final String RIGHT_KEY = "Right";
    private static final String LEFT_KEY = "Left";
    private static final String LOAD_FILE_PROMPT = "LoadFilePrompt";
    private static final String SELECT_PREFERENCES_PROMPT = "SelectPreferencesPrompt";

    private Group root = new Group();
    private UserCommandField myUserInput = new UserCommandField(WIDTH, HEIGHT);
    private DrawingCanvas myDrawingCanvas = new DrawingCanvas(WIDTH, HEIGHT);
    private LoadConfigPopup myCurrentLoadPopup;
    private SetPreferencesPopup myCurrentNewWindowPopup;
    private HistoryViewer myHistory = new HistoryViewer(COMMAND_COLUMN, DrawingCanvas.CANVAS_TOP_PADDING);
    private ScrollingWindow myNewCommandViewer = new CommandViewer(LIST_VIEW_COLUMN, DrawingCanvas.CANVAS_TOP_PADDING, this::setInputText);
    private ScrollingWindow myVariableView = new VariableViewer(LIST_VIEW_COLUMN, HALFWAY_DOWN);
    private LineManager myLineManager = new LineManager(root);
    private LanguageHelper languageHelper;
    private TurtleGraphicalMover myGraphicalMover;
    private LanguageSelector myLanguageSelector;
    private DisplayCustomizer myCustomizer;
    private List<Turtle> myTurtles = new ArrayList<>();
    private Map<StaticViewElement, List<StringProperty>> myStaticViewElements;
    private Label myCurrentErrorMessage = new Label();
    private VBox belowInputFieldItems = new VBox(BOX_SPACING);
    private HBox belowCanvasButtons = new HBox(BOX_SPACING);
    private Button myGo = new Button();
    private Button myClear = new Button();
    private Button myStop = new Button();
    private Button myNewWindow = new Button();
    private Button mySaveText = new Button();
    private Button mySaveVarsAndCommands = new Button();
    private Button loadVarsAndCommands = new Button();
    private Button loadFileButton = new Button();
    private Button undoButton = new Button();
    private Button redoButton = new Button();

    /**
     * Sets up all of the visual elements so that
     * the Main class doesn't have to do as much work
     *
     * @return
     */
    public Scene setupGame() {
        setupBox(belowInputFieldItems, UserCommandField.FIELD_SIDE_PADDING * 3 + myUserInput.getWidth(), DrawingCanvas.CANVAS_TOP_PADDING + myDrawingCanvas.getHeight() + BOX_SPACING * 5, myDrawingCanvas.getWidth());
        setupBox(belowCanvasButtons, DrawingCanvas.CANVAS_SIDE_PADDING, DrawingCanvas.CANVAS_TOP_PADDING + myDrawingCanvas.getHeight() + BOX_SPACING, myDrawingCanvas.getWidth());
        setButtons();
        setSelectors();
        myGraphicalMover = new TurtleGraphicalMover(myUserInput.getView().getLayoutX(), myUserInput.getView().getLayoutY() + GRAPHICAL_VIEWER_HEIGHT_OFFSET);
        myCustomizer = new DisplayCustomizer(belowCanvasButtons.getLayoutX(), belowCanvasButtons.getLayoutY() + BUTTON_HEIGHT_OFFSET + 10);
        setText();
        root.getChildren().addAll(belowInputFieldItems, belowCanvasButtons);
        for (StaticViewElement element : myStaticViewElements.keySet()) { root.getChildren().add(element.getView()); }
        myCurrentErrorMessage.setLayoutX(myVariableView.getView().getLayoutX());
        myCurrentErrorMessage.setLayoutY(myVariableView.getView().getLayoutY() + ERROR_MESSAGE_PADDING);
        root.getChildren().add(myCurrentErrorMessage);
        Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(MAIN_STYLESHEET)).toExternalForm());
        return scene;
    }

    /**
     * Allows the graphical mover to get the commands which work with backend and for the
     * graphical mover to move multiple active turtle
     *
     * @param executor
     */
    public void setDirectExecutor(DirectExecutor executor) {
        myGraphicalMover.setCommandNameProperties(languageHelper.getStringProperty(FORWARD_KEY), languageHelper.getStringProperty(RIGHT_KEY),
                languageHelper.getStringProperty(BACKWARD_KEY), languageHelper.getStringProperty(LEFT_KEY));
        myGraphicalMover.setButtons(executor, myLineManager);
        myHistory.setDirectExecutor(executor, myLineManager);
    }

    /**
     * Allows us to add a jumper to a common command page
     *
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

    /**
     * Allows controller to get the file which is loaded in the popup
     * in order to pass to backend
     *
     * @return
     */
    public File getFile() {
        myLineManager.newProgram();
        try {
            return myCurrentLoadPopup.getFile();
        } catch (FileDoesNotExistException err) {
            myCurrentErrorMessage.textProperty().setValue(err.getMessage());
            return null;
        }
    }

    /**
     * Allows the Go button to call the getInstuction() and myModel.execute() located in the controller
     * without having direct access to the model
     *
     * @param goAction
     */
    public void setGoButton(EventHandler<ActionEvent> goAction) {
        myGo.setOnAction(goAction);
    }

    /**
     * Allows the new window button to create a popup and for the popup go button to access
     * the makeNewWindow() method in the Controller
     *
     * @param newWindowAction
     * @param stage
     */
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

    /**
     * Allows us to create a popup using the stage from the controller
     *
     * @param s
     */
    public void setSaveTextFileButton(Stage s) {
        mySaveText.setOnAction(e -> createNewFileSaverPopup(s, myUserInput.getUserInput()));
    }

    /**
     * Allows us to create a popup with the current stage in controller. Also gives
     * controller method of myModel.execute(file) to the popup go button
     *
     * @param primaryStage
     */
    public void setLoadTextFileButton(EventHandler<ActionEvent> loadFileAction, Stage primaryStage) {
        loadFileButton.setOnAction(e -> createNewFileLoaderPopup(primaryStage, loadFileAction));
    }

    public void setLoadVarsAndCommands(EventHandler<ActionEvent> loadFileAction, Stage primaryStage) {
        loadVarsAndCommands.setOnAction(e -> createNewFileLoaderPopup(primaryStage, loadFileAction));
    }

    /**
     * Allows us to set the input prompt for the user
     *
     * @param command
     */
    public void setInputText(String command) {
        myUserInput.setUserInput(command);
    }

    /**
     * Binds our variable viewer to the backend list of variables
     *
     * @param variableList
     */
    public void setVariableList(ObservableList<Token> variableList) {
        myVariableView.bindList(variableList);
    }

    /**
     * Binds our new command list to the backend list of commands
     *
     * @param newCommandList
     */
    public void setNewCommandList(ObservableList<Token> newCommandList) {
        myNewCommandViewer.bindList(newCommandList);
    }

    /**
     * Binds our history list to the backend list of history
     *
     * @param historyList
     * @param undoDisabled
     * @param redoDisabled
     */
    public void setupHistory(ObservableList<Token> historyList, BooleanProperty undoDisabled, BooleanProperty redoDisabled) {
        myHistory.bindList(historyList);
        undoButton.disableProperty().bind(undoDisabled);
        redoButton.disableProperty().bind(redoDisabled);
    }

    /**
     * Allows us to clear the current history on front and backend, as well as the lines drawn on DrawingCanvas
     *
     * @param clearAction
     */
    public void setClearHistory(EventHandler<ActionEvent> clearAction) {
        myClear.setOnAction(e -> {
            myLineManager.clearAllLines();
            moveTurtlesToCenter();
            clearAction.handle(e);
        });
    }

    /**
     * Sets up undo button to delete lines and move the turtle back
     *
     * @param undoAction
     */
    public void setUndoButton(EventHandler<ActionEvent> undoAction) {
        undoButton.setOnAction(event -> {
            boolean tempPen = myGraphicalMover.getPenUp();
            myGraphicalMover.setPenUp(true);
            myLineManager.undo();
            undoAction.handle(event);
            myGraphicalMover.setPenUp(tempPen);
        });
    }

    /**
     * Sets up redo button to draw previous lines
     * and move the turtle
     *
     * @param redoAction
     */
    public void setRedoButton(EventHandler<ActionEvent> redoAction) {
        redoButton.setOnAction(event -> {
            boolean tempPen = myGraphicalMover.getPenUp();
            myGraphicalMover.setPenUp(true);
            myLineManager.redo();
            redoAction.handle(event);
            myGraphicalMover.setPenUp(tempPen);
        });
    }

    /**
     * Needed in order to create a newWindow in the Controller with preferences
     * chosen in the popup
     *
     * @return
     */
    public String getNewWindowPreferences() {
        return myCurrentNewWindowPopup.getPreference();
    }

    /**
     * Binds the error message to the backend so both ends can throw exceptions
     *
     * @param message
     */
    public void bindErrorMessage(StringProperty message) {
        myCurrentErrorMessage.textProperty().bindBidirectional(message);
        myCurrentErrorMessage.setTextFill(Color.RED);
    }

    public void setError(Exception e) {
        myCurrentErrorMessage.textProperty().set(e.getMessage());
    }

    /**
     * Creates a new screen manger to set up and link our visual elements
     * that interact with each other in the frontend
     *
     * @return
     */
    public ScreenManager getScreenManager() {
        return new ScreenManager(root, myUserInput, myTurtles, myDrawingCanvas, myLanguageSelector, myLineManager, myCustomizer, myGraphicalMover);
    }

    /**
     * Allows us to access backend variables to save to a file
     * @param saveNewCommandsAndVarsAction
     */
    public void setVarsAndCommandsSaveButton(EventHandler<ActionEvent> saveNewCommandsAndVarsAction) {
        mySaveVarsAndCommands.setOnAction(saveNewCommandsAndVarsAction);
    }

    /**
     * These two methods create popups to performs either loading or saving
     * @param s
     * @param stringToSave
     */
    public void createNewFileSaverPopup(Stage s, String stringToSave) {
        myCurrentLoadPopup = new LoadConfigPopup();
        myCurrentLoadPopup.setPromptProperty(languageHelper.getStringProperty(LOAD_FILE_PROMPT));
        myCurrentLoadPopup.setGoButtonProperty(languageHelper.getStringProperty(SAVE_BUTTON_KEY));
        myCurrentLoadPopup.getMyPopup().show(s);
        myCurrentLoadPopup.setPopupButton(e -> saveFile(myCurrentLoadPopup.getFilePackage(), stringToSave));
    }

    public void createNewFileLoaderPopup(Stage s, EventHandler<ActionEvent> loadFileAction) {
        myCurrentLoadPopup = new LoadConfigPopup();
        myCurrentLoadPopup.setPromptProperty(languageHelper.getStringProperty(LOAD_FILE_PROMPT));
        myCurrentLoadPopup.setGoButtonProperty(languageHelper.getStringProperty(GO_BUTTON_KEY));
        myCurrentLoadPopup.getMyPopup().show(s);
        myCurrentLoadPopup.setPopupButton(loadFileAction);
    }

    private void setupBox(Pane box, double x, double y, double width) {
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setMinWidth(width);
    }

    private void moveTurtlesToCenter() {
        boolean tempPen = myGraphicalMover.getPenUp();
        myGraphicalMover.setPenUp(true);
        for (Turtle t : myTurtles) {
            t.returnTurtleToDefault();
        }
        myGraphicalMover.setPenUp(tempPen);
    }

    private void setButtons() {
        myGo.setMinWidth(myUserInput.getWidth());
        myStop.setOnAction(e -> moveTurtlesToCenter());
        belowCanvasButtons.setMaxWidth(myDrawingCanvas.getWidth());
        belowCanvasButtons.setMinWidth(myDrawingCanvas.getWidth());
        belowCanvasButtons.setAlignment(Pos.CENTER);
        belowCanvasButtons.getChildren().addAll(myClear, myStop, undoButton, redoButton);
        belowInputFieldItems.getChildren().add(myGo);
        HBox newWindowButtons = new HBox(BOX_SPACING);
        newWindowButtons.setLayoutY(COMMON_COMMAND_BUTTON_HEIGHT_OFFSET);
        newWindowButtons.setLayoutX(WIDTH / 2.0 - BUTTON_HEIGHT_OFFSET * 4);
        newWindowButtons.getChildren().addAll(myNewWindow, loadFileButton, mySaveText);
        HBox variableCommandButtons = new HBox(BOX_SPACING);
        variableCommandButtons.setLayoutX(myVariableView.getView().getLayoutX());
        variableCommandButtons.setLayoutY(myVariableView.getView().getLayoutY() + SAVE_VARS_PADDING_Y);
        variableCommandButtons.setAlignment(Pos.CENTER);
        variableCommandButtons.getChildren().addAll(mySaveVarsAndCommands, loadVarsAndCommands);
        root.getChildren().addAll(newWindowButtons, variableCommandButtons);
    }

    private void setSelectors() {
        myLanguageSelector = new LanguageSelector(DrawingCanvas.CANVAS_SIDE_PADDING, DrawingCanvas.CANVAS_TOP_PADDING / 4);
    }

    private void setText() {
        languageHelper = new LanguageHelper(myLanguageSelector.getLanguageChoiceProperty());
        bindButton(myGo, GO_BUTTON_KEY);
        bindButton(myClear, CLEAR_BUTTON_KEY);
        bindButton(myStop, STOP_BUTTON_KEY);
        bindButton(myNewWindow, NEW_WINDOW_BUTTON_KEY);
        bindButton(loadFileButton, NEW_CONFIG_BUTTON_KEY);
        bindButton(undoButton, UNDO_BUTTON_KEY);
        bindButton(redoButton, REDO_BUTTON_KEY);
        bindButton(mySaveText, SAVE_BUTTON_KEY);
        bindButton(mySaveVarsAndCommands, SAVE_VARS_BUTTON_KEY);
        bindButton(loadVarsAndCommands, LOAD_VARS_BUTTON_KEY);
        setStaticViewElementMap();
        for (StaticViewElement element : myStaticViewElements.keySet()) {
            element.setTitleProperty(myStaticViewElements.get(element));
        }
    }


    private void bindButton(Button button, String key) {
        button.textProperty().bind(languageHelper.getStringProperty(key));
    }

    private void setStaticViewElementMap() {
        myStaticViewElements = new HashMap<>();
        myStaticViewElements.put(myDrawingCanvas, null);
        myStaticViewElements.put(myUserInput, null);
        myStaticViewElements.put(myHistory, Arrays.asList(keyToSP(HISTORY_TITLE_KEY)));
        myStaticViewElements.put(myNewCommandViewer, Arrays.asList(keyToSP(NEW_COMMAND_TITLE_KEY)));
        myStaticViewElements.put(myVariableView, Arrays.asList(keyToSP(VARIABLE_TITLE_KEY)));
        myStaticViewElements.put(myGraphicalMover, Arrays.asList(keyToSP(PEN_THICKNESS_TEXT_KEY), keyToSP(PEN_UP_BUTTON_KEY), keyToSP(PEN_DOWN_BUTTON_KEY)));
        myStaticViewElements.put(myCustomizer, Arrays.asList(keyToSP(BACKGROUND_SELECTOR_TEXT_KEY), keyToSP(PEN_SELECTOR_TEXT_KEY), keyToSP(TURTLE_SELECTOR_TEXT_KEY)));
        myStaticViewElements.put(myLanguageSelector, Arrays.asList(keyToSP(LANGUAGE_SELECTOR_TEXT_KEY)));
    }

    private StringProperty keyToSP(String key) {
        return languageHelper.getStringProperty(key);
    }

    private void saveFile(String newFilePackage, String stringToSave) {
        FileOutputStream out;
        try {
            out = new FileOutputStream(newFilePackage);
            PrintWriter writer = new PrintWriter(newFilePackage, FILE_CASE_PREFERENCE);
            writer.write(stringToSave);
            writer.close();
            out.close();
        } catch (FileNotFoundException e) {
            WriterCannotFindFileException error = new WriterCannotFindFileException(e);
            myCurrentErrorMessage.textProperty().setValue(error.getMessage());
        } catch (IOException e) {
            TextParsingException error = new TextParsingException(e);
            myCurrentErrorMessage.textProperty().setValue(error.getMessage());
        }
    }
}
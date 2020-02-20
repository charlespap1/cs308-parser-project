package slogo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.view.DrawingCanvas;
import slogo.view.HistoryCanvas;
import slogo.view.SetupScreen;
import slogo.view.Turtle;
import slogo.view.UserCommandField;
import slogo.view.View;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application implements View {
    /**
     * Start of the program.
     */
    public static final double BOX_SPACING = 10;
    public static final String TITLE = "SLogo";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;

    public static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
    public static final String MAIN_STYLESHEET = "main.css";

    private Scene myScene;
    private UserCommandField myUserInput;
    private Group root;
    private Turtle myTurtle;
    private DrawingCanvas myCanvas;
    private Button myGo;
    private Button myClear;
    private Button myStop;
    private HistoryCanvas myHistory;

    private SetupScreen mySetup;

    private Text myCurrentErrorMessage;


    /**
     * Allows us to set up the initial stage and animation
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        mySetup = new SetupScreen(WIDTH, HEIGHT, BACKGROUND, root);
        myScene = mySetup.setupGame();
        setButtons();
        //System.out.println(getClass().getResource(DEFAULT_RESOURCE_FOLDER + MAIN_STYLESHEET).toExternalForm());
        //myScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + MAIN_STYLESHEET).toExternalForm());

        primaryStage.setScene(myScene);
        primaryStage.setTitle(TITLE);
        primaryStage.show();

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

    }

    private void step (double elapsedTime){

    }

    private void setButtons()
    {
        myGo = mySetup.getGoButton();
        myGo.setOnAction(e -> getInstruction());

        myClear = mySetup.getClearButton();
        myClear.setOnAction(e -> clearCanvas());

        myStop = mySetup.getStopButton();
        myStop.setOnAction(e -> returnToDefaultTurtle());

        myTurtle = mySetup.getTurtle();
        myUserInput = mySetup.getUserInput();
        myCanvas = mySetup.getDrawingCanvas();
        myHistory = mySetup.getHistoryCanvas();
        myCurrentErrorMessage = mySetup.getCurrentErrorMessage();
    }

    /**
     * Method which can be called by any instance of a Visual object
     * and allows the caller to get the user input from the command input field
     * @return
     * @throws NullPointerException
     */
    @Override
    public String getInstruction() throws NullPointerException {
        String input = myUserInput.getUserInput();
        if(input.equals("hi"))
        {
            State newState = new State(50, 50, false, 0);
            updateDisplay(newState);
            newState = new State(50, 30, false, 0);
            updateDisplay(newState);
            newState = new State(0, -100, false, 0);
            updateDisplay(newState);
            newState = new State(-200, -200, false, -90);
            updateDisplay(newState);
        }
        else if(input.equals("bye"))
        {
            showError("you typed bye");
        }
        else if(input.equals("nope"))
        {
            showError("that's wrong");
        }
        return myUserInput.getUserInput();
    }

    /**
     * Updates the movement of the turtle according to new states
     * @param nextState
     */
    @Override
    public void updateDisplay(State nextState) {
        Line newLine = myTurtle.update(nextState);
        root.getChildren().add(newLine);
        root.getChildren().remove(myTurtle.getView());
        root.getChildren().add(myTurtle.getView());
        myCanvas.addLine(newLine);

        for(int i = 0; i < 5; i++)
        {
            String command = nextState.toString();
            myHistory.addHistory(command);
        }
    }

    /**
     * Displays a text error below the Go button when
     * the user inputs an unknown command
     * @param errorMessage
     */
    @Override
    public void showError(String errorMessage) {
        myCurrentErrorMessage.setText(errorMessage);
    }

    @Override
    public void changeCanvasColor(Color color) {
        myCanvas.changeBackground(color);
    }

    private void clearCanvas()
    {
        root.getChildren().removeAll(myCanvas.getLines());
    }

    private void returnToDefaultTurtle()
    {
        myTurtle.returnTurtleToDefault();
        clearCanvas();
    }
    public static void main (String[] args) {
        launch(args);
    }

}

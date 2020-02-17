package slogo;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.view.DrawingCanvas;
import slogo.view.GoButton;
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
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;

    public static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
    public static final String MAIN_STYLESHEET = "main.css";

    public static final String TURTLE_IMAGE = "turtle.png";

    private Scene myScene;
    private UserCommandField myUserInput;
    private Group root;
    private Turtle myTurtle;
    private DrawingCanvas myCanvas;
    private Button myGo;
    private Button myClear;
    private Button myStop;

    private List<Line> myLines;

    private VBox belowInputFieldItems;
    private HBox belowCanvasButtons;

    /**
     * Allows us to set up the initial stage and animation
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        myScene = setupGame(WIDTH, HEIGHT, BACKGROUND);
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

    private Scene setupGame (int width, int height, Paint background){
        root = new Group();
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE));

        myLines = new ArrayList<>();
        myCanvas = new DrawingCanvas(WIDTH, HEIGHT);
        myTurtle = new Turtle(image, myCanvas.getWidth(), myCanvas.getHeight());
        myUserInput = new UserCommandField(WIDTH, HEIGHT);

        setVBoxLayout();
        setHBoxLayout();
        setButtons();

        root.getChildren().addAll(myCanvas.getView(), myTurtle.getView(), myUserInput.getView(), belowInputFieldItems, belowCanvasButtons);

        Scene scene = new Scene(root, width, height, background);
        return scene;
    }

    private void step (double elapsedTime){

    }

    private void setHBoxLayout()
    {
        belowCanvasButtons = new HBox(BOX_SPACING);
        belowCanvasButtons.setLayoutX(DrawingCanvas.CANVAS_SIDE_PADDING);
        belowCanvasButtons.setLayoutY(DrawingCanvas.CANVAS_TOP_PADDING + myCanvas.getHeight() + BOX_SPACING);
        belowCanvasButtons.setMinWidth(myCanvas.getWidth());
        belowCanvasButtons.setAlignment(Pos.CENTER);
    }


    private void setVBoxLayout()
    {
        belowInputFieldItems = new VBox(BOX_SPACING);
        belowInputFieldItems.setLayoutY(UserCommandField.FIELD_TOP_PADDING + myUserInput.getHeight() + BOX_SPACING);
        belowInputFieldItems.setLayoutX(UserCommandField.FIELD_SIDE_PADDING*3 + myUserInput.getWidth());
        belowInputFieldItems.setMinWidth(myUserInput.getWidth());
        belowInputFieldItems.setAlignment(Pos.CENTER);
    }

    private void setButtons()
    {
        myGo = new Button(GoButton.BUTTON_TEXT);
        myGo.setMinWidth(myUserInput.getWidth());
        myGo.setOnAction(e -> getInstruction());
        belowInputFieldItems.getChildren().add(myGo);


        myClear = new Button("Clear Canvas");
        myClear.setMinWidth(myCanvas.getWidth()/2 - BOX_SPACING);
        myClear.setOnAction(e -> clearCanvas());
        belowCanvasButtons.getChildren().add(myClear);

        myStop = new Button("Stop Turtle");
        myStop.setMinWidth(myCanvas.getWidth()/2 - BOX_SPACING);
        myStop.setOnAction(e -> returnTurtle());
        belowCanvasButtons.getChildren().add(myStop);
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
            newState = new State(50, 200, false, 0);
            updateDisplay(newState);
            newState = new State(200, 200, false, 0);
            updateDisplay(newState);
            newState = new State(200, 400, false, -90);
            updateDisplay(newState);
        }
        return myUserInput.getUserInput();
    }

    /**
     * Updates the movement of the turtle according to new states
     * @param nextState
     */
    @Override
    public void updateDisplay(State nextState) {
        Line newLine = myTurtle.update(nextState, root);
        myLines.add(newLine);
    }

    /**
     * Displays a text error below the Go button when
     * the user inputs an unknown command
     * @param errorMessage
     */
    @Override
    public void showError(String errorMessage) {
        Text error = new Text(errorMessage);
        belowInputFieldItems.getChildren().add(error);
    }
    private void clearCanvas()
    {
        root.getChildren().removeAll(myLines);
    }

    private void returnTurtle()
    {
        myTurtle.returnTurtleToDefault();
        clearCanvas();
    }

    public static void main (String[] args) {
        launch(args);
    }


}

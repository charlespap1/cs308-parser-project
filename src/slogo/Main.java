package slogo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.view.Interactions;
import slogo.view.SetupScreen;

/**
 * Main method where the GUI comes together
 * @author Juliet
 */
public class Main extends Application{
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
    private Group root;

    private SetupScreen mySetup;
    private Interactions myInteractions;


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
        myInteractions = new Interactions(mySetup);
        //setNeededVars();
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

    public static void main (String[] args) {
        launch(args);
    }

}

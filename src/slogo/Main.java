package slogo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.view.DrawingCanvas;
import slogo.view.Turtle;
import slogo.view.UserCommandField;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    /**
     * Start of the program.
     */
    public static final String TITLE = "SLogo";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;

    public static final String TURTLE_IMAGE = "turtle.png";

    private Scene myScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        myScene = setupGame(WIDTH, HEIGHT, BACKGROUND);
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
        Group root = new Group();
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE));

        DrawingCanvas dc = new DrawingCanvas(WIDTH, HEIGHT);
        Turtle t = new Turtle(image, dc.getWidth(), dc.getHeight());
        UserCommandField c = new UserCommandField(WIDTH, HEIGHT);
        root.getChildren().addAll(dc.getView(), t.getView(), c.getView());

        Scene scene = new Scene(root, width, height, background);
        // respond to input
        // scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        // scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return scene;
    }

    private void step (double elapsedTime){

    }


    public static void main (String[] args) {
        launch(args);
        System.out.println("Hello world");
    }

}

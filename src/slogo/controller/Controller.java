package slogo.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.model.ModelAPI;
import slogo.model.Turtle;

/**
 * Main method where the GUI comes together
 * @author Juliet
 */
public class Controller extends Application {

    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private ModelAPI myModel;
    private ViewAPI myView;
    /**
     * Allows us to set up the initial stage and animation
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        myModel = new ModelAPI();
        myView = new ViewAPI();
        Turtle myTurtle = myModel.getTurtle();
        myView.setProperties(myTurtle);

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step (double elapsedTime){
        //do we need all of the timing things for animations? is anything timed?
    }


    /**
     * Method which can be called by any instance of a Visual object
     * and allows the caller to get the user input from the command input field
     * @return
     * @throws NullPointerException
     */
    public void getInstruction() throws NullPointerException {
        //make this get called when button is pressed in front end -- set some kind of event listener? ask TA
        String input = myView.getUserInput();
        myModel.executeCode(input);
    }

    public static void main (String[] args) {
        launch(args);
    }

}

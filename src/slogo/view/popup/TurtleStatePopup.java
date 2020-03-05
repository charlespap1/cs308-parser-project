package slogo.view.popup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import slogo.view.Turtle;

import java.util.ArrayList;
import java.util.List;

public class TurtleStatePopup {
    public static final int HOLDER_SPACING = 10;
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 100;
    public static final Color HOLDER_BACKGROUND_COLOR = Color.LAVENDER;
    public static final int HOLDER_PADDING = 5;

    private Popup myPopup;
    private List<Turtle> myTurtles;

    private HBox myHolder;
    private VBox myTurtleStates;
    private Text myPrompt;
    private Button exit;
    private Button myGo;

    public TurtleStatePopup(List<Turtle> initTurtles){
        myTurtles = initTurtles;
        myPopup = new Popup();

        myHolder = new HBox(HOLDER_SPACING);

        myHolder.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        myHolder.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        myHolder.setBackground(new Background(new BackgroundFill(HOLDER_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        myTurtleStates = new VBox(HOLDER_SPACING);

        for(Turtle t : myTurtles){
            Label label = new Label(String.format("X: %f  Y: %f  PenUp: %b",
                    t.getXPos(), t.getYPos(), t.getPenUp()));
            myTurtleStates.getChildren().add(label);
        }

        exit = new Button("Exit");
        exit.setOnAction(event -> myPopup.hide());

        myHolder.setAlignment(Pos.CENTER);
        myHolder.setPadding(new Insets(HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING));

        myHolder.getChildren().addAll(exit, myGo, myTurtleStates);

        myPopup.getContent().add(myHolder);
    }


    public void show(Stage currentStage){
        updateView();
        myPopup.show(currentStage);
    }

    public void hide(Stage currentStage){
        myPopup.hide();
    }

    /**
     * Called whenever the frontend calls AddNewTurtle; updates the internal list of turtles and the visuals
     * @param newTurtle: new turtle to be added to the list
     */
    public void addTurtle(Turtle newTurtle) {
        myTurtles.add(newTurtle);
        updateView();
    }

    private void updateView(){
        myTurtleStates.getChildren().clear();
        for(Turtle t : myTurtles){
            Label label = new Label(String.format("X: %f  Y: %f  PenUp: %b",
                    t.getXPos(), t.getYPos(), t.getPenUp()));
            myTurtleStates.getChildren().add(label);
        }
    }
}

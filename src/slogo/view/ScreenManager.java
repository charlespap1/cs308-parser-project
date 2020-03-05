package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import slogo.view.commonCommands.CommonCommands;
import slogo.view.scrollers.CommandViewer;
import slogo.view.scrollers.HistoryCanvas;
import slogo.view.scrollers.ListViewer;
import slogo.view.scrollers.VariableViewer;
import slogo.view.selectors.BackgroundSelector;
import slogo.view.selectors.LanguageSelector;
import slogo.view.selectors.PenSelector;
import slogo.view.selectors.TurtleFaceSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScreenManager {
    // TODO: shouldn't use default
    public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";

    private Group myRoot;
    private UserCommandField myUserInput;
    private List<Turtle> myTurtles;
    private DrawingCanvas myDrawingCanvas;
    private HistoryCanvas myHistory;
    private LanguageSelector myLanguageSelector;


    public ScreenManager(Group root, UserCommandField commandField, List<Turtle> turtles, DrawingCanvas canvas, HistoryCanvas history,
                         LanguageSelector languages){
        myRoot = root;
        myUserInput = commandField;
        myTurtles = turtles;
        myDrawingCanvas = canvas;
        myHistory = history;
        myLanguageSelector = languages;
    }


    public void addNewTurtle(slogo.model.Turtle turtle) {
        Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
        Turtle newTurtle = new Turtle(image, myDrawingCanvas.getWidth(), myDrawingCanvas.getHeight());
        myTurtles.add(newTurtle);
        myRoot.getChildren().add(newTurtle.getView());
        newTurtle.setProperties(turtle);
        turtle.pointProperty().addListener((o, oldVal, newVal) -> update(newTurtle));
        turtle.currCommandProperty().addListener((o, oldVal, newVal) -> addHistory(newVal));
    }


    public String getUserInput() { return myUserInput.getUserInput(); }

    public void addHistory(String command) { myHistory.addHistory(command);}

    public StringProperty getLanguageChoice() { return myLanguageSelector.getLanguageChoiceProperty(); }

    /**
     * Updates the movement of the turtle according to new states
     */
    private void update(Turtle newTurtle) {
        Line newLine = newTurtle.drawLineAndBound();
        if (newLine!=null) {
            myRoot.getChildren().add(newLine);
            myDrawingCanvas.addLine(newLine);
            newTurtle.getView().toFront();
        }
    }
}

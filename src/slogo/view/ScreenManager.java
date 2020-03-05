package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import slogo.view.scrollers.HistoryViewer;
import slogo.view.selectors.LanguageSelector;

import java.util.List;
import java.util.Objects;

public class ScreenManager {
    // TODO: shouldn't use default
    public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";

    private Group myRoot;
    private UserCommandField myUserInput;
    private List<Turtle> myTurtles;
    private DrawingCanvas myDrawingCanvas;
    private LanguageSelector myLanguageSelector;


    public ScreenManager(Group root, UserCommandField commandField, List<Turtle> turtles, DrawingCanvas canvas,
                         LanguageSelector languages){
        myRoot = root;
        myUserInput = commandField;
        myTurtles = turtles;
        myDrawingCanvas = canvas;
        myLanguageSelector = languages;
    }


    public void addNewTurtle(slogo.model.Turtle turtle) {
        Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
        Turtle newTurtle = new Turtle(image, myDrawingCanvas.getWidth(), myDrawingCanvas.getHeight());
        myTurtles.add(newTurtle);
        myRoot.getChildren().add(newTurtle.getView());
        newTurtle.setProperties(turtle);
        turtle.pointProperty().addListener((o, oldVal, newVal) -> update(newTurtle));
    }

    public String getUserInput() { return myUserInput.getUserInput(); }

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

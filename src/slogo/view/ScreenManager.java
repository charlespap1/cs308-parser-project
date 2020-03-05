package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
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
    private LineManager myLineManager;


    public ScreenManager(Group root, UserCommandField commandField, List<Turtle> turtles, DrawingCanvas canvas,
                         LanguageSelector languages, LineManager lineManager){
        myRoot = root;
        myUserInput = commandField;
        myTurtles = turtles;
        myDrawingCanvas = canvas;
        myLanguageSelector = languages;
        myLineManager = lineManager;
    }


    public void addNewTurtle(slogo.model.Turtle turtle) {
        Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
        Turtle newTurtle = new Turtle(image, myDrawingCanvas.getWidth(), myDrawingCanvas.getHeight());
        myTurtles.add(newTurtle);
        myRoot.getChildren().add(newTurtle.getView());
        newTurtle.setProperties(turtle);
        turtle.pointProperty().addListener((o, oldVal, newVal) -> update(newTurtle));
    }

    public String getUserInput() {
        myLineManager.newProgram();
        return myUserInput.getUserInput();
    }

    public StringProperty getLanguageChoice() { return myLanguageSelector.getLanguageChoiceProperty(); }

    /**
     * Updates the movement of the turtle according to new states
     */
    private void update(Turtle turtle) {
        Line newLine = turtle.drawLineAndBound();
        if (newLine!=null) {
            myLineManager.addLine(newLine);
            turtle.getView().toFront();
        }
    }
}

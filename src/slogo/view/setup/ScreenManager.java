package slogo.view.setup;

import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.view.DrawingCanvas;
import slogo.view.LineManager;
import slogo.view.Turtle;
import slogo.view.TurtleGraphicalMover;
import slogo.view.UserCommandField;
import slogo.view.selectors.DisplayCustomizer;
import slogo.view.selectors.LanguageSelector;

import java.util.List;
import java.util.Objects;

public class ScreenManager {
    public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";

    private Group myRoot;
    private UserCommandField myUserInput;
    private List<Turtle> myTurtles;
    private DrawingCanvas myDrawingCanvas;
    private LanguageSelector myLanguageSelector;
    private LineManager myLineManager;
    private DisplayCustomizer myDisplayCustomizer;
    private TurtleGraphicalMover myGraphicalMover;

    public ScreenManager(Group root, UserCommandField commandField, List<Turtle> turtles, DrawingCanvas canvas,
                         LanguageSelector languages, LineManager lineManager, DisplayCustomizer displayCustomizer, TurtleGraphicalMover mover){
        myRoot = root;
        myUserInput = commandField;
        myTurtles = turtles;
        myDrawingCanvas = canvas;
        myLanguageSelector = languages;
        myLineManager = lineManager;
        myDisplayCustomizer = displayCustomizer;
        myGraphicalMover = mover;
        setButtons();
    }

    public void setPreferences(String preferences){
        PreferenceLoaderSelector.setPreferences(preferences, myDisplayCustomizer, myGraphicalMover);
        myTurtles.get(0).changeImage(myDisplayCustomizer.getImage(myDisplayCustomizer.getImageIndex()));
        myDrawingCanvas.changeBackground(myDisplayCustomizer.getColor(myDisplayCustomizer.getBackgroundIndex()));
    }

    public String getUserInput() {
        myLineManager.newProgram();
        return myUserInput.getUserInput();
    }

    public StringProperty getLanguageChoice() { return myLanguageSelector.getLanguageChoiceProperty(); }

    public void addNewTurtle(slogo.model.Turtle turtle) {
        Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
        Turtle newTurtle = new Turtle(image, myDrawingCanvas.getWidth(), myDrawingCanvas.getHeight());
        myTurtles.add(newTurtle);
        myRoot.getChildren().add(newTurtle.getView());
        newTurtle.setProperties(turtle);
        turtle.pointProperty().addListener((o, oldVal, newVal) -> update(newTurtle));
        myRoot.getChildren().add(newTurtle.buildPopup());
    }

    public int setBackground(List<Double> params) {
        int index = params.get(0).intValue();
        myDisplayCustomizer.setBackground(index);
        Color color = myDisplayCustomizer.getColor(index);
        myDrawingCanvas.changeBackground(color);
        return index;
    }

    public int setShape(List<Double> params){
        int index = params.get(0).intValue();
        Image image = myDisplayCustomizer.getImage(index);
        for (Turtle turtle : myTurtles) turtle.changeImage(image);
        return index;
    }

    public int setPenColor(List<Double> params) {
        int index = params.get(0).intValue();
        myDisplayCustomizer.setPenColor(index);
        return index;
    }

    public int setPenUp(List<Double> params){
        myGraphicalMover.setPenUp(true);
        return 0;
    }

    public int setPenDown(List<Double> params){
        myGraphicalMover.setPenUp(false);
        return 1;
    }

    public int setPenSize(List<Double> params){
        int thickness = params.get(0).intValue();
        if(thickness > 5) thickness = 5;
        else if (thickness < 1) thickness = 1;
        myGraphicalMover.setPenWidth(thickness);
        return thickness;
    }

    public int setPalette(List<Double> params){
        int index = params.get(0).intValue();
        String r = String.valueOf(params.get(1).intValue());
        String g = String.valueOf(params.get(2).intValue());
        String b = String.valueOf(params.get(3).intValue());
        myDisplayCustomizer.setPalette(index, r, g, b);
        return index;
    }

    public int getPenDown(List<Double> params){ return myGraphicalMover.getPenUp() ? 0 : 1; }
    public int getPenColor(List<Double> params) { return myDisplayCustomizer.getPenIndex(); }
    public int getShape(List<Double> params) { return myDisplayCustomizer.getImageIndex();  }

    public int clearScreen(List<Double> params) {
        for (Turtle t : myTurtles) t.returnTurtleToDefault();
        myLineManager.clearAllLines();
        return 0;
    }

    /**
     * Updates the movement of the turtle according to new states
     */
    private void update(Turtle turtle) {
        Line newLine = turtle.drawLineAndBound(myGraphicalMover.getPenUp());
        if (newLine!=null) {
            newLine.setStroke(myDisplayCustomizer.getColor(myDisplayCustomizer.getPenIndex()));
            newLine.setStrokeWidth(myGraphicalMover.getPenWidth());
            myLineManager.addLine(newLine);
            turtle.getView().toFront();
        }
    }

    private void setButtons(){
        myDisplayCustomizer.setButtons(this::setBackground, this::setShape);
    }
}

package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.view.selectors.DisplayCustomizer;
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
    }

    public void setPreferences(String preferences){
        PreferenceLoaderSelector.setPreferences(preferences, myDisplayCustomizer);
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

    public int setBackground(List<Double> params) {
        int index = params.get(0).intValue();
        Color color = myDisplayCustomizer.getColor(index);
        myDrawingCanvas.changeBackground(color);
        return index;
    }

    public int setPenColor(List<Double> params) {
        int index = params.get(0).intValue();
        myDisplayCustomizer.setPenColor(index);
        return index;
    }

    //TODO
    public int setPenThickness(List<Double> params){
        int thickness = params.get(0).intValue();
        if(thickness > 5) thickness = 5;
        else if (thickness < 1) thickness = 1;
        //TODO: implement with palette ? unsure
        //myGraphicalMover.setSlider(thickness);
        return thickness;
    }

    public int setShape(List<Double> params){
        int index = params.get(0).intValue();
        Image image = myDisplayCustomizer.getImage(index);
        System.out.println("changing");
        for (Turtle turtle : myTurtles) turtle.changeImage(image);
        return index;
    }

    public int setPalette(List<Double> params){
        int index = params.get(0).intValue();
        String r = String.valueOf(params.get(1).intValue());
        String g = String.valueOf(params.get(2).intValue());
        String b = String.valueOf(params.get(3).intValue());
        myDisplayCustomizer.setPalette(index, r, g, b);
        return index;
    }

    public int getPenColor(List<Double> params) { return myDisplayCustomizer.getPenIndex(); }
    public int getShape(List<Double> params) { return myDisplayCustomizer.getImageIndex();  }

    //TODO
    public int clearScreen(List<Double> params) {
        //myHistory.clearHistory();
        for (Turtle t : myTurtles) t.returnTurtleToDefault();
        myLineManager.clearAllLines();
        return 0;
    }

    public StringProperty getLanguageChoice() { return myLanguageSelector.getLanguageChoiceProperty(); }

    /**
     * Updates the movement of the turtle according to new states
     */
    private void update(Turtle turtle) {
        Line newLine = turtle.drawLineAndBound();
        if (newLine!=null) {
            newLine.setStroke(myDisplayCustomizer.getColor(myDisplayCustomizer.getPenIndex()));
            //newLine.setStrokeWidth(currentWidth);
            myLineManager.addLine(newLine);
            turtle.getView().toFront();
        }
    }
}

package slogo.model;

import javafx.beans.property.*;

public class Turtle {

    private DoubleProperty turtleX;
    private DoubleProperty turtleY;
    private DoubleProperty turtleAngle;
    private BooleanProperty penUp;
    private BooleanProperty visible = new SimpleBooleanProperty(true);
    private StringProperty currCommand = new SimpleStringProperty();

    public Turtle(int xPos, int yPos, boolean isPenUp, int angle) {
        turtleX = new SimpleDoubleProperty(xPos);
        turtleY = new SimpleDoubleProperty(yPos);
        turtleAngle = new SimpleDoubleProperty(angle);
        penUp = new SimpleBooleanProperty(isPenUp);
    }

    public DoubleProperty turtleXProperty(){ return turtleX; }
    public DoubleProperty turtleYProperty(){ return turtleY; }
    public DoubleProperty turtleAngleProperty(){ return turtleAngle; }
    public BooleanProperty penUpProperty(){ return penUp; }
    public BooleanProperty visibleProperty(){ return visible; }
    public StringProperty currCommandProperty(){ return currCommand; }

    // update all at once so that we know y is set last, drawline in view/turtle will work
    // would also probably work to update all individually if that's easier, as long as y is set last
    public void setLocation(double x, double y) {
        turtleX.setValue(x);
        turtleY.setValue(y);
    }

    public void setVisible(boolean isVisible) { visible.set(isVisible); }
    public void setAngle(double angle) { turtleAngle.setValue(angle); }
    public void setPenUp(boolean isPenUp) { penUp.setValue(isPenUp);}
    public void setCurrCommand(String command) { currCommand.setValue(command); }

    public double getXPos() {
        return turtleX.getValue();
    }
    public double getYPos() {
        return turtleY.getValue();
    }
    public boolean getIsPenUp() {
        return penUp.getValue();
    }
    public double getAngle() { return turtleAngle.getValue(); }
}

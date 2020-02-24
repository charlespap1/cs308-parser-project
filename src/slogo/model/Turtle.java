package slogo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Turtle {

    private DoubleProperty turtleX;
    private DoubleProperty turtleY;
    private DoubleProperty turtleAngle;
    private BooleanProperty penUp;
    private BooleanProperty visible;


    public Turtle(int xPos, int yPos, boolean isPenUp, int angle) {
        turtleX = new SimpleDoubleProperty(xPos);
        turtleY = new SimpleDoubleProperty(yPos);
        turtleAngle = new SimpleDoubleProperty(angle);
        penUp = new SimpleBooleanProperty(isPenUp);
        visible = new SimpleBooleanProperty(true);
    }

    public DoubleProperty turtleXProperty(){ return turtleX; }
    public DoubleProperty turtleYProperty(){ return turtleY; }
    public DoubleProperty turtleAngleProperty(){ return turtleAngle; }
    public BooleanProperty penUpProperty(){ return penUp; }
    public BooleanProperty visibleProperty(){ return visible; }

    // update all at once so that we know y is set last, drawline in view/turtle will work
    // would also probably work to update all individually if that's easier, as long as y is set last
    public void setProperties(double x, double y, double angle, boolean isPenUp){
        turtleAngle.setValue(angle);
        penUp.setValue(isPenUp);
        turtleX.setValue(x);
        turtleY.setValue(y);
    }

    public void setVisible(boolean isVisible) { visible.set(isVisible); }
    public void setAngle(double angle) { turtleAngle.setValue(angle); }
    public void setPenUp(boolean isPenUp) { penUp.setValue(isPenUp);}

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

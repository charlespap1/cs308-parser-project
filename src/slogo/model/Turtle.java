package slogo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Turtle {

    private double xPos;
    private double yPos;
    private boolean isPenUp;
    private double angle;

    private DoubleProperty turtleX;
    private DoubleProperty turtleY;
    private DoubleProperty turtleAngle;
    private BooleanProperty penUp;


    public Turtle(int xPos, int yPos, boolean isPenUp, int angle) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isPenUp = isPenUp;
        this.angle = angle;
        turtleX = new SimpleDoubleProperty(xPos);
        turtleY = new SimpleDoubleProperty(yPos);
        turtleAngle = new SimpleDoubleProperty(angle);
        penUp = new SimpleBooleanProperty(isPenUp);
    }

    public DoubleProperty turtleXProperty(){ return turtleX; }
    public DoubleProperty turtleYProperty(){ return turtleY; }
    public DoubleProperty turtleAngleProperty(){ return turtleAngle; }
    public BooleanProperty penUpProperty(){ return penUp; }

    // update all at once so that we know y is set last, drawline in view/turtle will work
    // would also probably work to update all individually if that's easier, as long as y is set last
    public void setProperties(double x, double y, double angle, boolean isPenUp){
        turtleAngle.setValue(angle);
        penUp.setValue(isPenUp);
        turtleX.setValue(x);
        turtleY.setValue(y);
    }

    public double getxPos() {
        return this.xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return this.yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public boolean getIsPenUp() {
        return this.isPenUp;
    }

    public void setPenUp(boolean isPenUp) {
        this.isPenUp = isPenUp;
    }

    public double getAngle() {
        return this.angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}

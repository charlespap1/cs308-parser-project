package slogo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Turtle {

    private int xPos;
    private int yPos;
    private boolean isPenUp;
    private int angle;

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

    public void setProperties(double x, double y, double angle, boolean isPenUp){
        turtleAngle.setValue(angle);
        penUp.setValue(isPenUp);
        turtleX.setValue(x);
        turtleY.setValue(y);
    }

    public int getxPos() {
        return this.xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return this.yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean getIsPenUp() {
        return this.isPenUp;
    }

    public void setPenUp(boolean isPenUp) {
        this.isPenUp = isPenUp;
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}

package slogo.model.history;

import slogo.model.Turtle;

public class State {
    private double id;
    private double xPos;
    private double yPos;
    private boolean isPenUp;
    private double angle;

    public State(double id, double xPos, double yPos, boolean isPenUp, double angle) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.isPenUp = isPenUp;
        this.angle = angle;
    }

    public State(Turtle t) {
        id = t.getId();
        xPos = t.getXPos();
        yPos = t.getYPos();
        isPenUp = t.getIsPenUp();
        angle = t.getAngle();
    }

    public double getId() {
        return id;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public boolean getIsPenUp() {
        return isPenUp;
    }

    public double getAngle() {
        return angle;
    }
}

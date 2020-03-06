package slogo.model.history;

import slogo.model.Turtle;

public class State {
    private double id;
    private double xPos;
    private double yPos;
    private double angle;
    private boolean isActive;
    private boolean isVisible;

    public State(Turtle t) {
        id = t.getId();
        xPos = t.getXPos();
        yPos = t.getYPos();
        angle = t.getAngle();
        isActive = t.isActive();
        isVisible = t.isVisible();
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

    public double getAngle() {
        return angle;
    }

    public boolean getIsActive() { return isActive; }

    public boolean getIsVisible() { return isVisible; }
}

package slogo.model.history;

import slogo.model.Turtle;

/**
 * Stores a state for an individual turtle.
 *
 * @author Michael, Natalie
 */
public class State {
    private double id;
    private double xPos;
    private double yPos;
    private double angle;
    private boolean isActive;
    private boolean isVisible;

    /**
     * Constructs new turtle state based off given Turtle
     * @param t Turtle to get state info from
     */
    public State(Turtle t) {
        id = t.getId();
        xPos = t.getXPos();
        yPos = t.getYPos();
        angle = t.getAngle();
        isActive = t.isActive();
        isVisible = t.isVisible();
    }

    /**
     * Getter for ID number
     * @return ID number
     */
    public double getId() {
        return id;
    }

    /**
     * Getter for x-coordinate.
     * @return x-coordinate
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Getter for y-coordinate.
     * @return y-coordinate
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Getter for angle.
     * @return angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Getter for active status.
     * @return true if turtle is active
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Getter for visibility status.
     * @return true if turtle is visible
     */
    public boolean getIsVisible() {
        return isVisible;
    }
}

package slogo;

import slogo.view.Turtle;

public class State {

    public static final double TURTLE_FACTOR = Turtle.TURTLE_FACTOR;

    private double x;
    private double y;
    boolean isPenUp;
    double angleFacing;

    public State(double x, double y, boolean isPenUp, double angleFacing) {
        this.x = x + TURTLE_FACTOR;
        this.y = y + TURTLE_FACTOR;
        this.isPenUp = isPenUp;
        this.angleFacing = angleFacing;
    }

    /**
     * Allows the turtle to be able to move and change based on
     * the new x, y and pen up of the new state
     * @return
     */
    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public boolean isPenUp()
    {
        return isPenUp;
    }

    public double getAngleFacing()
    {
        return angleFacing;
    }

}

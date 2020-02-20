package slogo;


public class State {

    private double x;
    private double y;
    boolean isPenUp;
    double angleFacing;

    public State(double x, double y, boolean isPenUp, double angleFacing) {
        this.x = x;
        this.y = y;
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

    public String toString()
    {
        return String .format("     State %.2f, %.2f, Pen is up: %b, Facing angle %f", x, y, isPenUp, angleFacing);
    }

}

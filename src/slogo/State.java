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
}

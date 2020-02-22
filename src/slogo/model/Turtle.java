package slogo.model;

public class Turtle {

    private double xPos;
    private double yPos;
    private boolean isPenUp;
    private double angle;

    public Turtle(int xPos, int yPos, boolean isPenUp, int angle) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isPenUp = isPenUp;
        this.angle = angle;
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

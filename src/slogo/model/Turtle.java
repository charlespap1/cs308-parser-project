package slogo.model;

public class Turtle {

    private int xPos;
    private int yPos;
    private boolean isPenUp;
    private int angle;

    public Turtle(int xPos, int yPos, boolean isPenUp, int angle) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isPenUp = isPenUp;
        this.angle = angle;
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

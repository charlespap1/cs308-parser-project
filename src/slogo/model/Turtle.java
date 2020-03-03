package slogo.model;

import javafx.beans.property.*;

import java.awt.geom.Point2D;


public class Turtle {

    private DoubleProperty turtleX;
    private DoubleProperty turtleY;
    private DoubleProperty turtleAngle;
    private BooleanProperty penUp;
    private BooleanProperty visible = new SimpleBooleanProperty(true);
    private StringProperty currCommand = new SimpleStringProperty();
    private ObjectProperty<Point2D> coordinates;
    private DoubleProperty penColor;
    private DoubleProperty shape;
    private int id;

    public Turtle(int id, int xPos, int yPos, boolean isPenUp, int angle) {
        this.id = id;
        turtleX = new SimpleDoubleProperty(xPos);
        turtleY = new SimpleDoubleProperty(yPos);
        turtleAngle = new SimpleDoubleProperty(angle);
        penUp = new SimpleBooleanProperty(isPenUp);
        coordinates = new SimpleObjectProperty<>(new Point2D.Double(xPos, yPos));
    }

    public DoubleProperty turtleXProperty(){ return turtleX; }
    public DoubleProperty turtleYProperty(){ return turtleY; }
    public DoubleProperty turtleAngleProperty(){ return turtleAngle; }
    public BooleanProperty penUpProperty(){ return penUp; }
    public BooleanProperty visibleProperty(){ return visible; }
    public StringProperty currCommandProperty(){ return currCommand; }
    public ObjectProperty<Point2D> pointProperty() { return coordinates; }
    public DoubleProperty penColorProperty() { return penColor; }
    public DoubleProperty shapeProperty() { return shape; }

    public void setLocation(double x, double y) {
        turtleX.setValue(x);
        turtleY.setValue(y);
        coordinates.setValue(new Point2D.Double(x, y));
    }

    public void setVisible(boolean isVisible) { visible.set(isVisible); }
    public void setAngle(double angle) { turtleAngle.setValue(angle); }
    public void setPenUp(boolean isPenUp) { penUp.setValue(isPenUp);}
    public void setCurrCommand(String command) { currCommand.setValue(command); }
    public void setPenColor(double penColor) { this.penColor.setValue(penColor); }
    public void setShape(double shape) { this.shape.setValue(shape); }

    public double getXPos() {
        return turtleX.getValue();
    }
    public double getYPos() {
        return turtleY.getValue();
    }
    public boolean getIsPenUp() {
        return penUp.getValue();
    }
    public double getAngle() { return turtleAngle.getValue(); }
    public boolean isVisible() { return visible.getValue(); }
    public int getId() { return id; }
    public double getPenColor() { return penColor.getValue(); }
    public double getShape() { return shape.getValue(); }
}

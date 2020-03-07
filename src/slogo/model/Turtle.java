package slogo.model;

import javafx.beans.property.*;

import java.awt.geom.Point2D;

/**
 * Class to handle back end information needed for each turtle.
 */
public class Turtle {

    private static final int DEFAULT_X = 0;
    private static final int DEFAULT_Y = 0;
    private static final int DEFAULT_ANGLE = 90;

    private DoubleProperty turtleX = new SimpleDoubleProperty();
    private DoubleProperty turtleY = new SimpleDoubleProperty();
    private DoubleProperty turtleAngle = new SimpleDoubleProperty();
    private BooleanProperty visible = new SimpleBooleanProperty(true);
    private ObjectProperty<Point2D> coordinates = new SimpleObjectProperty<>(new Point2D.Double(turtleX.get(), turtleY.get()));
    private BooleanProperty active = new SimpleBooleanProperty(true);
    private double id;

    public Turtle(double id) {
        this.id = id;
        setDefault();
    }

    public void setDefault() {
        setLocation(DEFAULT_X, DEFAULT_Y);
        setAngle(DEFAULT_ANGLE);
        setVisible(true);
        setActive(true);
    }

    public DoubleProperty turtleXProperty() {
        return turtleX;
    }

    public DoubleProperty turtleYProperty() {
        return turtleY;
    }

    public DoubleProperty turtleAngleProperty() {
        return turtleAngle;
    }

    public BooleanProperty visibleProperty() {
        return visible;
    }

    public ObjectProperty<Point2D> pointProperty() {
        return coordinates;
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setLocation(double x, double y) {
        turtleX.setValue(x);
        turtleY.setValue(y);
        coordinates.setValue(new Point2D.Double(x, y));
    }

    public void setVisible(boolean isVisible) {
        visible.set(isVisible);
    }

    public void setAngle(double angle) {
        turtleAngle.setValue(angle);
    }

    public void setActive(boolean isActive) {
        active.setValue(isActive);
    }

    public double getXPos() {
        return turtleX.getValue();
    }

    public double getYPos() {
        return turtleY.getValue();
    }

    public double getAngle() {
        return turtleAngle.getValue();
    }

    public boolean isVisible() {
        return visible.getValue();
    }

    public boolean isActive() {
        return active.getValue();
    }

    public double getId() {
        return id;
    }
}

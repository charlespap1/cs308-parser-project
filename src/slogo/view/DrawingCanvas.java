package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * This class specifies the attributes of the
 * drawing screen where the turtle lives and
 * new lines are drawn according to the user input
 *
 * @author Juliet
 */
public class DrawingCanvas implements StaticViewElement {

    public static final double CANVAS_TOP_PADDING = 50;
    public static final double CANVAS_BOTTOM_PADDING = 195;
    public static final double CANVAS_SIDE_PADDING = 10;
    private static final double ARC_RADIUS = 10;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color BORDER_COLOR = Color.LIGHTBLUE;

    private double canvasWidth;
    private double canvasHeight;
    private Rectangle myView = new Rectangle();

    public DrawingCanvas(double screenWidth, double screenHeight) {
        canvasWidth = screenWidth / 3 - 2 * CANVAS_SIDE_PADDING;
        canvasHeight = screenHeight - CANVAS_TOP_PADDING - CANVAS_BOTTOM_PADDING;
        setBody();
    }

    /**
     * Allows the canvas to be added to the root of the main class
     *
     * @return
     */
    public Node getView() {
        return myView;
    }

    /**
     * Only other static view element without a Title
     *
     * @param sp
     */
    @Override
    public void setTitleProperty(List<StringProperty> sp) {

    }

    /**
     * These methods allow us to get size of the canvas so
     * we can place the turtle in the middle of it
     *
     * @return
     */
    public double getWidth() {
        return canvasWidth;
    }

    public double getHeight() {
        return canvasHeight;
    }

    /**
     * This will be needed when we add color changing option to the background
     *
     * @param color
     */
    public void changeBackground(Color color) {
        myView.setFill(color);
    }

    private void setBody() {
        myView.setX(CANVAS_SIDE_PADDING);
        myView.setY(CANVAS_TOP_PADDING);
        myView.setWidth(canvasWidth);
        myView.setHeight(canvasHeight);
        myView.setArcWidth(ARC_RADIUS);
        myView.setArcHeight(ARC_RADIUS);
        myView.setFill(BACKGROUND_COLOR);
        myView.setStroke(BORDER_COLOR);
    }

}

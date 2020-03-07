package slogo.view.setup;

import slogo.view.TurtleGraphicalMover;
import slogo.view.selectors.DisplayCustomizer;

import java.util.ResourceBundle;

/**
 * Class that helps set the preferences of the new window based
 * on the preference sheet specified
 *
 * @author Juliet, Natalie
 */

public class PreferenceLoaderSelector {
    private static final String FILE_PATH = "resources.preferences.";
    private static final String DEFAULT_PREFERENCE = "resources.preferences.DefaultPreferences";

    private static final String PEN_COLOR_KEY = "PenColor";
    private static final String TURTLE_IMAGE_KEY = "TurtleImage";
    private static final String BACKGROUND_KEY = "Background";
    private static final String PEN_UP_KEY = "PenUp";
    private static final String PEN_WIDTH_KEY = "PenWidth";

    private static final int DEFAULT_TURTLE_IMAGE = 0;
    private static final boolean DEFAULT_PEN_UP = false;
    private static final int DEFAULT_PEN_COLOR = 0;
    private static final int DEFAULT_BACKGROUND_COLOR = 7;
    private static final double DEFAULT_PEN_WIDTH = 1.0;

    public static void setPreferences(String preferenceFile, DisplayCustomizer myCustomizer, TurtleGraphicalMover myMover) {
        ResourceBundle myPreferences;
        try {
            myPreferences = ResourceBundle.getBundle(FILE_PATH + preferenceFile);
        } catch (Exception e) {
            myPreferences = ResourceBundle.getBundle(DEFAULT_PREFERENCE);
        }
        setDisplayCustomizer(myPreferences, myCustomizer);
        setGraphicalMover(myPreferences, myMover);
    }

    private static void setDisplayCustomizer(ResourceBundle myPreferences, DisplayCustomizer myCustomizer) {
        int penColor;
        int imageIndex;
        int backgroundColor;
        try {
            penColor = Integer.parseInt(myPreferences.getString(PEN_COLOR_KEY));
            imageIndex = Integer.parseInt(myPreferences.getString(TURTLE_IMAGE_KEY));
            backgroundColor = Integer.parseInt(myPreferences.getString(BACKGROUND_KEY));
        } catch (Exception e) {
            penColor = DEFAULT_PEN_COLOR;
            imageIndex = DEFAULT_TURTLE_IMAGE;
            backgroundColor = DEFAULT_BACKGROUND_COLOR;
        }
        myCustomizer.setPenColor(penColor);
        myCustomizer.getImage(imageIndex);
        myCustomizer.setBackground(backgroundColor);
    }

    private static void setGraphicalMover(ResourceBundle myPreferences, TurtleGraphicalMover myMover) {
        boolean penUp;
        double penWidth;
        try {
            penUp = Boolean.parseBoolean(myPreferences.getString(PEN_UP_KEY));
            penWidth = Integer.parseInt(myPreferences.getString(PEN_WIDTH_KEY));
        } catch (Exception e) {
            penUp = DEFAULT_PEN_UP;
            penWidth = DEFAULT_PEN_WIDTH;
        }
        myMover.setPenUp(penUp);
        myMover.setPenWidth(penWidth);
    }
}



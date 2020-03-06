package slogo.view.setup;

import slogo.view.TurtleGraphicalMover;
import slogo.view.selectors.DisplayCustomizer;

import java.util.ResourceBundle;

/**
 * Class that helps set the preferences of the new window based
 * on the preference sheet specified 
 */

public class PreferenceLoaderSelector {
    public static final String FILE_PATH = "resources.preferences.";
  public static final String DEFAULT_PREFERENCE = "resources.preferences.DefaultPreferences";

  public static final String PEN_COLOR_KEY = "PenColor";
  public static final String TURTLE_IMAGE_KEY = "TurtleImage";
  public static final String BACKGROUND_KEY = "Background";
  public static final String PEN_UP_KEY = "PenUp";
  public static final String PEN_WIDTH_KEY = "PenWidth";

  public static final int DEFAULT_TURTLE_IMAGE = 0;
  public static final boolean DEFAULT_PEN_UP = false;
  public static final int DEFAULT_PEN_COLOR = 0;
  public static final int DEFAULT_BACKGROUND_COLOR = 7;
  public static final double DEFAULT_PEN_WIDTH = 1.0;


  public static void setPreferences(String preferenceFile, DisplayCustomizer myCustomizer, TurtleGraphicalMover myMover){
      ResourceBundle myPreferences;
      try {
          myPreferences = ResourceBundle.getBundle(FILE_PATH + preferenceFile);
      } catch (Exception e) {
          myPreferences = ResourceBundle.getBundle(DEFAULT_PREFERENCE);
      }

      int penColor;
      try { penColor = Integer.parseInt(myPreferences.getString(PEN_COLOR_KEY));
      } catch (Exception e){ penColor = DEFAULT_PEN_COLOR; }
      myCustomizer.setPenColor(penColor);

      int imageIndex;
      try { imageIndex = Integer.parseInt(myPreferences.getString(TURTLE_IMAGE_KEY));
      } catch(Exception e){ imageIndex = DEFAULT_TURTLE_IMAGE; }
      myCustomizer.getImage(imageIndex);

      int backgroundColor;
      try { backgroundColor = Integer.parseInt(myPreferences.getString(BACKGROUND_KEY));
      } catch (Exception e){ backgroundColor = DEFAULT_BACKGROUND_COLOR; }
      myCustomizer.setBackground(backgroundColor);

      boolean penUp;
      try{ penUp = Boolean.parseBoolean(myPreferences.getString(PEN_UP_KEY));
      } catch(Exception e) { penUp = DEFAULT_PEN_UP; }
      myMover.setPenUp(penUp);

      double penWidth;
      try { penWidth = Integer.parseInt(myPreferences.getString(PEN_WIDTH_KEY));
      } catch (Exception e){ penWidth = DEFAULT_PEN_WIDTH; }
      myMover.setPenWidth(penWidth);
  }
}



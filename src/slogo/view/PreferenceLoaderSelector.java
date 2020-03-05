package slogo.view;

import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PreferenceLoaderSelector {

  public static final String DEFAULT_PREFERENCE = "resources.preferences.PreferencesOne";
  public static final String BACKGROUND_COLORS_PACKAGE = "resources.colors.BackgroundColors";

  public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";
  public static final boolean DEFAULT_PEN_UP = false;
  public static final String DEFAULT_PEN_COLOR = "0,0,0";
  public static final String DEFAULT_BACKGROUND_COLOR = "255,255,255";

  public static final String FILE_PATH = "resources.preferences.";

  private ResourceBundle myPreferences;

  public PreferenceLoaderSelector(String preferenceFile)
  {
    myPreferences = ResourceBundle.getBundle(FILE_PATH + preferenceFile);
  }

  public PreferenceLoaderSelector()
  {
    myPreferences = ResourceBundle.getBundle(DEFAULT_PREFERENCE);
  }

  public void setTurtle(Turtle t)
  {
    String penRGB = getStringValueFromPackage(myPreferences.getString("PenColor"), BACKGROUND_COLORS_PACKAGE, DEFAULT_PEN_COLOR);

    RGBHelper rgbHelper = new RGBHelper();
    Color penColor = rgbHelper.getColor(penRGB);


    String imageFileName = getImageFilenameFromPackage(myPreferences.getString("TurtleImage"));
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imageFileName)));

    boolean penUp = getStringBooleanFromPackage(myPreferences.getString("PenUp"), DEFAULT_PEN_UP);

    t.changePenColor(penColor);
    t.changeImage(image);
    t.setPenUp(penUp);
  }

  public void changeBackground(DrawingCanvas dc)
  {
    String backgroundRGB = getStringValueFromPackage(myPreferences.getString("Background"),BACKGROUND_COLORS_PACKAGE, DEFAULT_BACKGROUND_COLOR);
    RGBHelper rgbHelper = new RGBHelper();
    Color backgroundColor = rgbHelper.getColor(backgroundRGB);

    dc.changeBackground(backgroundColor);
  }

  private String getStringValueFromPackage(String key, String filePackage, String defaultValue)
  {
    ResourceBundle resource = ResourceBundle.getBundle(filePackage);

    try {
      return resource.getString(key);
    }
    catch(Exception e){
      return defaultValue;
    }

  }

  private boolean getStringBooleanFromPackage(String key, boolean defaultValue)
  {
    try {
      return Boolean.parseBoolean(myPreferences.getString(key));
    }
    catch(Exception e){
      return defaultValue;
    }
  }

  private String getImageFilenameFromPackage(String key)
  {
    try {
      return myPreferences.getString(key);
    }
    catch(Exception e){
      return DEFAULT_TURTLE_IMAGE;
    }
  }



}



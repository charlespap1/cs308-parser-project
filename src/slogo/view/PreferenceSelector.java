package slogo.view;

import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PreferenceSelector {

  public static final String DEFAULT_PREFERENCE = "resources.preferences.PreferencesOne";
  public static final String BACKGROUND_COLORS_PACKAGE = "resources.colors.BackgroundColors";
  public static final String PEN_COLORS_PACKAGE = "resources.colors.PenColors";

  public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";
  public static final boolean DEFAULT_PEN_UP = false;
  public static final String DEFAULT_PEN_COLOR = "#000000";
  public static final String DEFAULT_BACKGROUND_COLOR = "#ffffff";

  public static final String FILE_PATH = "resources.preferences.";

  private ResourceBundle myPreferences;

  public PreferenceSelector(String preferenceFile)
  {
    myPreferences = ResourceBundle.getBundle(FILE_PATH + preferenceFile);
  }

  public PreferenceSelector()
  {
    myPreferences = ResourceBundle.getBundle(DEFAULT_PREFERENCE);
  }

  public void setTurtle(Turtle t)
  {
    String penHex = getStringValueFromPackage(myPreferences.getString("PenColor"),PEN_COLORS_PACKAGE, DEFAULT_PEN_COLOR);

    Color penColor = Color.web(penHex);

    String imageFileName = getImageFilenameFromPackage(myPreferences.getString("TurtleImage"));
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imageFileName)));

    boolean penUp = getStringBooleanFromPackage(myPreferences.getString("PenUp"), DEFAULT_PEN_UP);

    t.changePenColor(penColor);
    t.changeImage(image);
    t.setPenUp(penUp);
  }

  public void changeBackground(DrawingCanvas dc)
  {
    String backgroundHex = getStringValueFromPackage(myPreferences.getString("Background"),BACKGROUND_COLORS_PACKAGE, DEFAULT_BACKGROUND_COLOR);
    Color backgroundColor = Color.web(backgroundHex);

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



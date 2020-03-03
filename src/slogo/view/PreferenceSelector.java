package slogo.view;

import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PreferenceSelector {

  public static final String DEFAULT_PREFERENCE = "resources.preferences.PreferencesOne";
  public static final String BACKGROUND_COLORS_PACKAGE = "resources.colors.BackgroundColors";
  public static final String PEN_COLORS_PACKAGE = "resources.colors.PenColors";

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
    String penHex = getValueFromPackage(myPreferences.getString("PenColor"),PEN_COLORS_PACKAGE, "#000000");

    Color penColor = Color.web(penHex);

    String imageFileName = myPreferences.getString("TurtleImage");
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imageFileName)));

    boolean penUp = Boolean.parseBoolean(myPreferences.getString("PenUp"));

    t.changePenColor(penColor);
    t.changeImage(image);
    t.setPenUp(penUp);
  }

  public void changeBackground(DrawingCanvas dc)
  {
    String backgroundHex = getValueFromPackage(myPreferences.getString("Background"),BACKGROUND_COLORS_PACKAGE, "#ffffff");
    Color backgroundColor = Color.web(backgroundHex);

    dc.changeBackground(backgroundColor);
  }

  public String getValueFromPackage(String key, String filePackage, String defaultValue)
  {
    ResourceBundle resource = ResourceBundle.getBundle(filePackage);

    try {
      return resource.getString(key);
    }
    catch(Exception e){
      return defaultValue;
  }

  }

}



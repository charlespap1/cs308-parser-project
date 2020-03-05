package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DisplayCustomizer {

  public static final int BOX_SPACING = 5;
  public static final double COLOR_SELECTOR_HEIGHT = 17;
  public static final String DEFAULT_BACKGROUND_SETTER = "-fx-background-color: rgb(%s)";
  private static final String COLOR_PATH = "resources.colors.BackgroundColors";
  private static final String IMAGE_PATH = "resources.commands.TurtleImages";

  public static final ResourceBundle myImageResource = ResourceBundle.getBundle(COLOR_PATH);
  public static final ResourceBundle myColorResource = ResourceBundle.getBundle(IMAGE_PATH);

  private List<Button> backgroundButtons;
  private List<Button> penButtons;
  private List<Button> imageButtons;


  protected VBox myHolder = new VBox(BOX_SPACING);
  protected HBox myBackgroundHolder = new HBox(BOX_SPACING);
  protected HBox myPenHolder = new HBox(BOX_SPACING);
  protected HBox myCharacterHolder = new HBox(BOX_SPACING);

  protected List<String> colors = new ArrayList<>();
  protected List<String> turtleFaces = new ArrayList<>();

  private List<String> colorKeys;
  private List<String> imageKeys;

  public DisplayCustomizer(double x, double y) {
    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);

    colorKeys = Collections.list(myColorResource.getKeys());
    imageKeys = Collections.list(myImageResource.getKeys());

    myHolder.getChildren().addAll(myBackgroundHolder, myCharacterHolder, myPenHolder);

    backgroundButtons = createButtons(colorKeys);
    penButtons = createButtons(colorKeys);
    imageButtons = createButtons(imageKeys);
    buildLists();
  }

  private List<Button> createButtons(List<String> ids)
  {
    List<Button> buttons = new ArrayList<>();
    for(String id : ids)
    {
      VBox buttonHold = new VBox(BOX_SPACING);
      Button newColor = new Button();
      buttons.add(newColor);
      newColor.setMaxHeight(COLOR_SELECTOR_HEIGHT);
      newColor.setMinHeight(COLOR_SELECTOR_HEIGHT);
      Text index = new Text(id);

      buttonHold.getChildren().addAll(newColor, index);
      buttonHold.setAlignment(Pos.CENTER);
      myBackgroundHolder.getChildren().add(buttonHold);
      myPenHolder.getChildren().add(buttonHold);
    }
    return buttons;
  }

  private void buildLists(){

    for (String key: colorKeys){
      String rgb = myColorResource.getString(key);
      colors.add(rgb);
      int index = Integer.parseInt(key);
      backgroundButtons.get(index).setStyle(String.format(DEFAULT_BACKGROUND_SETTER, rgb));
      penButtons.get(index).setStyle(String.format(DEFAULT_BACKGROUND_SETTER, rgb));
    }

    for(String key: imageKeys)
    {
      String filename = myImageResource.getString(key);
      turtleFaces.add(filename);
      turtleFaces.add(filename);
      int index = Integer.parseInt(key);
      Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(filename)));
      ImageView iv = new ImageView(image);
      imageButtons.get(index).setGraphic(iv);
    }

  }


  public Color getColor(String rgb)
  {
    String [] rgbVals = rgb.split(",");
    int r = Integer.parseInt(rgbVals[0]);
    int g = Integer.parseInt(rgbVals[1]);
    int b = Integer.parseInt(rgbVals[2]);

    return Color.rgb(r,g,b);
  }




}

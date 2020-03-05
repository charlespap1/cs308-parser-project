package slogo.view.selectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

  public static final ResourceBundle myImageResource = ResourceBundle.getBundle(IMAGE_PATH);
  public static final ResourceBundle myColorResource = ResourceBundle.getBundle(COLOR_PATH);

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

  private int penIndex = 0;
  private int backgroundIndex = 0;
  private int imageIndex = 0;

  public DisplayCustomizer(double x, double y) {
    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);

    colorKeys = Collections.list(myColorResource.getKeys());
    imageKeys = Collections.list(myImageResource.getKeys());

    myHolder.getChildren().addAll(myBackgroundHolder, myCharacterHolder, myPenHolder);

    backgroundButtons = createButtons(colorKeys, myBackgroundHolder);
    penButtons = createButtons(colorKeys, myPenHolder);
    imageButtons = createButtons(imageKeys, myCharacterHolder);
    buildLists();
  }

  public int getPenIndex(){ return penIndex; }
  public int getBackgroundIndex(){ return backgroundIndex; }
  public int getImageIndex(){ return imageIndex; }

  private List<Button> createButtons(List<String> ids, HBox holder)
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
      holder.getChildren().add(buttonHold);
    }
    return buttons;
  }

  public Node getView()
  {
    return myHolder;
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
      int index = Integer.parseInt(key);
      Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(filename)));
      ImageView iv = new ImageView(image);
      iv.setPreserveRatio(true);
      iv.setFitHeight(COLOR_SELECTOR_HEIGHT);
      imageButtons.get(index).setGraphic(iv);
    }

  }

  public void setTitleProperty(StringProperty background, StringProperty pen, StringProperty character){
    Text backgroundLabel = new Text();
    backgroundLabel.textProperty().bind(background);
    myBackgroundHolder.getChildren().add(0, backgroundLabel);

    Text penLabel = new Text();
    penLabel.textProperty().bind(pen);
    myBackgroundHolder.getChildren().add(0, penLabel);

    Text imageLabel = new Text();
    imageLabel.textProperty().bind(character);
    myBackgroundHolder.getChildren().add(0, imageLabel);
  }


  private Color getColor(String rgb)
  {
    String [] rgbVals = rgb.split(",");
    int r = Integer.parseInt(rgbVals[0]);
    int g = Integer.parseInt(rgbVals[1]);
    int b = Integer.parseInt(rgbVals[2]);

    return Color.rgb(r,g,b);
  }




}

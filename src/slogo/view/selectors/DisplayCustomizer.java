package slogo.view.selectors;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import slogo.view.DisplayAction;
import slogo.view.Turtle;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DisplayCustomizer {

  public static final int BOX_SPACING = 5;
  public static final double COLOR_SELECTOR_HEIGHT = 17;
  public static final String DEFAULT_BACKGROUND_SETTER = "-fx-background-color: rgb(%s)";
  private static final String COLOR_PATH = "resources.colors.BackgroundColors";
  private static final String IMAGE_PATH = "resources.commands.TurtleImages";
  private static final int DEFAULT_PEN_COLOR = 0;
  private static final int DEFAULT_TURTLE_FACE = 0;
  private static final int DEFAULT_BACKGROUND_COLOR = 7;

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

  private int penColorIndex = DEFAULT_PEN_COLOR;
  private int imageIndex = DEFAULT_TURTLE_FACE;
  private int backgroundColorIndex = DEFAULT_BACKGROUND_COLOR;
  private boolean penUp = false;

  public DisplayCustomizer(double x, double y) {
    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);
    myHolder.setAlignment(Pos.CENTER);

    colorKeys = Collections.list(myColorResource.getKeys());
    imageKeys = Collections.list(myImageResource.getKeys());

    myHolder.getChildren().addAll(myBackgroundHolder, myCharacterHolder, myPenHolder);

    backgroundButtons = createButtons(colorKeys, myBackgroundHolder);
    penButtons = createButtons(colorKeys, myPenHolder);
    imageButtons = createButtons(imageKeys, myCharacterHolder);
    buildLists();

    myBackgroundHolder.setAlignment(Pos.CENTER_RIGHT);
    myCharacterHolder.setAlignment(Pos.CENTER_RIGHT);
    myPenHolder.setAlignment(Pos.CENTER_RIGHT);


  }

  public int getPenIndex(){ return penColorIndex; }
  public int getImageIndex(){ return imageIndex; }
  public boolean getPenUp(){ return penUp; }
  public int getBackgroundIndex(){ return backgroundColorIndex; }
  public Color getColor(int index){ return getColor(colors.get(index)); }
  public Image getImage(int index){
    imageIndex = index;
    return new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(turtleFaces.get(index))));
  }
  public void setPalette(int index, String r, String g, String b){
    String color = r + "," + g + "," + b;
    colors.add(index, color);
  }
  public void setPenColor(int index){ penColorIndex = index; }
  public void setPenUp(boolean isPenUp){ penUp = isPenUp; }
  public void setBackground(int index){ backgroundColorIndex = index; }

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

  public Node getView() { return myHolder; }


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

  public void setButtons(DisplayAction penChangeAction, DisplayAction backgroundChangeAction, DisplayAction imageChangeAction) {
    for (int i = 0; i<penButtons.size(); i++){
      List<Double> index = new ArrayList<>();
      index.add((double) i);
      penButtons.get(i).setOnAction(e -> penChangeAction.execute(index));
      backgroundButtons.get(i).setOnAction(e -> backgroundChangeAction.execute(index));
    }
    for (int i = 0; i<imageButtons.size(); i++){
      List<Double> index = new ArrayList<>();
      index.add((double) i);
      imageButtons.get(i).setOnAction(e -> imageChangeAction.execute(index));
    }
  }

  public void setTitleProperty(StringProperty background, StringProperty pen, StringProperty character){
    Text backgroundLabel = new Text();
    backgroundLabel.textProperty().bind(background);
    myBackgroundHolder.getChildren().add(0, backgroundLabel);

    Text penLabel = new Text();
    penLabel.textProperty().bind(pen);
    myPenHolder.getChildren().add(0, penLabel);

    Text imageLabel = new Text();
    imageLabel.textProperty().bind(character);
    myCharacterHolder.getChildren().add(0, imageLabel);
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

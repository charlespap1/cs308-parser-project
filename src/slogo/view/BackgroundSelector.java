package slogo.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BackgroundSelector {

  public static final int HBOX_SPACING = 10;
  public static final List<String> BACKGROUND_COLORS = new ArrayList<>(Arrays.asList("White", "Red", "Magenta", "Blue", "Green", "Black"));
  private static final String RESOURCES = "resources";
  public static final String COMMAND_RESOURCE_PACKAGE = RESOURCES + ".commands.BackgroundColors";
  public static final String DEFAULT_BACKGROUND_SETTER = "-fx-background-color: ";
  public static final String TITLE = "Background colors: ";
  public static final double COLOR_SELECTOR_HEIGHT = 17;

  private ResourceBundle myResources;
  private HBox myHolder;
  private DrawingCanvas dc;

  public BackgroundSelector(DrawingCanvas dc, double x, double y)
  {
    myHolder = new HBox(HBOX_SPACING);
    Text title = new Text(TITLE);
    myHolder.getChildren().add(title);
    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);
    this.dc = dc;
    myResources = ResourceBundle.getBundle(COMMAND_RESOURCE_PACKAGE);
    setButtons();

  }

  private void setButtons()
  {
    for(String currColor: BACKGROUND_COLORS){
      Button newColor = new Button();
      newColor.setMaxHeight(COLOR_SELECTOR_HEIGHT);
      newColor.setMinHeight(COLOR_SELECTOR_HEIGHT);
      String hex = myResources.getString(currColor);
      newColor.setStyle(DEFAULT_BACKGROUND_SETTER + hex);

      newColor.setOnAction(e -> changeBackground(hex));

      myHolder.getChildren().add(newColor);
    }
  }

  private void changeBackground(String hexColor)
  {
    Color color = Color.web(hexColor);
    dc.changeBackground(color);
  }

  public Node getView()
  {
    return myHolder;
  }



}

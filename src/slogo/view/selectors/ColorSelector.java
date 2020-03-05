package slogo.view.selectors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.StaticViewElement;

/**
 * This class allows us to remove duplicated constructor
 * code of the Pen, Background and TurtleFace selectors
 * @author Juliet
 */

public abstract class ColorSelector implements StaticViewElement {
  public static final int BOX_SPACING = 5;
  public static final double COLOR_SELECTOR_HEIGHT = 17;
  public static final String DEFAULT_BACKGROUND_SETTER = "-fx-background-color: rgb(%s)";

  protected VBox myHolder = new VBox(BOX_SPACING);
  protected HBox myTopElements = new HBox(BOX_SPACING);
  private List<String> myIdentifiers;
  private ResourceBundle myResources;
  private Text myTitle = new Text();

  protected Map colorMap = new HashMap<Integer, String>();

  public ColorSelector(double x, double y, List<String> identifiers, String resourcePackage) {

    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);
    myHolder.getChildren().addAll(myTopElements);

    myResources = ResourceBundle.getBundle(resourcePackage);
    myIdentifiers = identifiers;
  }

  /**
   * Performs the action of changing whatever element
   * of the selector is clicked
   * @param rgb
   */
  protected abstract void changeAppearance(String rgb);

  /**
   * Allows for adding the selector to the root
   * @return
   */
  public Node getView()
  {
    return myHolder;
  }

  /**
   * Sets color or view and layout of button given the resource class it's reading from
   */
  protected void setColorButtons() {
    for (String identifier: myIdentifiers) {
      VBox buttonHold = new VBox(BOX_SPACING);
      Button newColor = new Button();
      newColor.setMaxHeight(COLOR_SELECTOR_HEIGHT);
      newColor.setMinHeight(COLOR_SELECTOR_HEIGHT);
      String rgb = myResources.getString(identifier);
      int idIntVal = Integer.parseInt(identifier);
      setButtonFromResourceResult(idIntVal, newColor, rgb);

      Text index = new Text(identifier);
      buttonHold.getChildren().addAll(newColor, index);
      buttonHold.setAlignment(Pos.CENTER);
      myTopElements.getChildren().add(buttonHold);
    }
  }

  public void setTitleProperty(StringProperty  sp){
    myTitle.textProperty().bind(sp);
    myTopElements.getChildren().add(0, myTitle);
  }

  public Map<String, String> map()
  {
    return colorMap;
  }


  /**
   * Gets the style of the button from the resource given
   * @param newColor
   * @param rgb
   */
  protected void setButtonFromResourceResult(int id, Button newColor, String rgb) {

    colorMap.put(id, rgb);
    newColor.setStyle(String.format(DEFAULT_BACKGROUND_SETTER,rgb));
    newColor.setOnAction(e -> changeAppearance(rgb));
  }

}
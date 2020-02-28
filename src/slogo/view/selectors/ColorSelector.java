package slogo.view.selectors;

import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * This class allows us to remove duplicated constructor
 * code of the Pen, Background and TurtleFace selectors
 * @author Juliet
 */

public abstract class ColorSelector{
  public static final int HBOX_SPACING = 10;
  public static final double COLOR_SELECTOR_HEIGHT = 17;
  public static final String DEFAULT_BACKGROUND_SETTER = "-fx-background-color: ";

  private HBox myHolder = new HBox(HBOX_SPACING);
  private List<String> myIdentifiers;
  private ResourceBundle myResources;
  private Text myTitle = new Text();

  public ColorSelector(double x, double y, List<String> identifiers, String resourcePackage) {

    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);

    myResources = ResourceBundle.getBundle(resourcePackage);
    myIdentifiers = identifiers;
  }

  /**
   * Performs the action of changing whatever element
   * of the selector is clicked
   * @param hex
   */
  protected abstract void changeAppearance(String hex);

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
      Button newColor = new Button();
      newColor.setMaxHeight(COLOR_SELECTOR_HEIGHT);
      newColor.setMinHeight(COLOR_SELECTOR_HEIGHT);
      String hex = myResources.getString(identifier);
      setButtonFromResourceResult(newColor, hex);
      myHolder.getChildren().add(newColor);
    }
  }

  public void setTitleProperty(StringProperty  sp){
    myTitle.textProperty().bind(sp);
    myHolder.getChildren().add(0, myTitle);
  }

  /**
   * Gets the style of the button from the resource given
   * @param newColor
   * @param hex
   */
  protected void setButtonFromResourceResult(Button newColor, String hex) {
    newColor.setStyle(DEFAULT_BACKGROUND_SETTER + hex);
    newColor.setOnAction(e -> changeAppearance(hex));
  }

}
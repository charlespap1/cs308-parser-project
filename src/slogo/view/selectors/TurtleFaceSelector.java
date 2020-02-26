package slogo.view.selectors;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

/**
 * An extension of the Color Selector which controls the Turtle Image Selector in the GUI. The Color Selector wasn't made
 * for this purpose, but its use of Strings rather than a color-specific object makes it flexible enough to be used here.
 * Unfortunately, using the Color Selector means some method and variable names may be misleading. Our apologies.
 */
public class TurtleFaceSelector extends ColorSelector {
  public static final List<String> IMAGES = new ArrayList<>(Arrays.asList("Turtle", "Airplane", "Car", "Dog"));
  private static final String RESOURCES = "resources";
  private static final String TITLE = "Character types: ";
  public static final String IMAGE_RESOURCE_PACKAGE = RESOURCES + ".commands.TurtleImages";

  private List<String> myIdentifiers;
  private ResourceBundle myResources;

  private HBox myHolder;

  public TurtleFaceSelector(double x, double y)
  {
    super(TITLE, x, y, IMAGES, IMAGE_RESOURCE_PACKAGE);
    myResources = ResourceBundle.getBundle(IMAGE_RESOURCE_PACKAGE);
    setColorButtons();
  }

  @Override
  protected void setButtonFromResourceResult(Button newImageButton, String imageFileName) {
    Image image;
    image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imageFileName)));
    ImageView iv = new ImageView(image);
    iv.setPreserveRatio(true);
    iv.setFitHeight(COLOR_SELECTOR_HEIGHT);
    newImageButton.setGraphic(iv);
    newImageButton.setOnAction(e -> changeSomething(imageFileName));
  }

  @Override
  public void changeSomething(String imageFileName)
  {
    changeTurtleImage(imageFileName);
  }

  private void changeTurtleImage(String imageFileName) {
    System.out.println("Change color to " + imageFileName);
  }

}

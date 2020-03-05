package slogo.view.selectors;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.view.Turtle;

import java.security.cert.TrustAnchor;
import java.util.*;

/**
 * An extension of the Color Selector which controls the Turtle Image Selector in the GUI. The Color Selector wasn't made
 * for this purpose, but its use of Strings rather than a color-specific object makes it flexible enough to be used here.
 * Unfortunately, using the Color Selector means some method and variable names may be misleading. Our apologies.
 * @author Braeden
 */
public class TurtleFaceSelector extends ColorSelector {
  public static final List<String> IMAGES = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
  public static final String RESOURCES = "resources";
  public static final String IMAGE_RESOURCE_PACKAGE = RESOURCES + ".commands.TurtleImages";

  private List<Turtle> turtles;

  public TurtleFaceSelector(List<Turtle> turtleList, double x, double y) {
    super(x, y, IMAGES, IMAGE_RESOURCE_PACKAGE);
    setColorButtons();
    turtles = turtleList;
  }

  @Override
  protected void setButtonFromResourceResult(int id, Button newImageButton, String imageFileName) {
    //TODO get filename from index
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imageFileName)));
    ImageView iv = new ImageView(image);
    iv.setPreserveRatio(true);
    iv.setFitHeight(COLOR_SELECTOR_HEIGHT);
    newImageButton.setGraphic(iv);
    newImageButton.setOnAction(e -> changeAppearance(id));
  }

  @Override
  public void changeAppearance(int index) {
    //TODO: get filename - index mappings
    setImage("car.png");
  }

  private void setImage(String filename){
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(filename)));
    for (Turtle t:turtles) t.changeImage(image);
  }
}

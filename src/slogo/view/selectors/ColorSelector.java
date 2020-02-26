package slogo.view.selectors;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public abstract class ColorSelector{
  public static final int HBOX_SPACING = 10;
  public static final double COLOR_SELECTOR_HEIGHT = 17;
  public static final String DEFAULT_BACKGROUND_SETTER = "-fx-background-color: ";

  private HBox myHolder;
  private List<String> myIdentifiers;
  private ResourceBundle myResources;

  public ColorSelector(String intro, double x, double y, List<String> identifiers, String resourcePackage)
  {
    myHolder = new HBox(HBOX_SPACING);
    Text title = new Text(intro);
    myHolder.getChildren().add(title);
    myHolder.setLayoutX(x);
    myHolder.setLayoutY(y);

    myResources = ResourceBundle.getBundle(resourcePackage);

    myIdentifiers = identifiers;
  }

  public void setColorButtons()
  {
    for(String identifier: myIdentifiers){
      Button newColor = new Button();
      newColor.setMaxHeight(COLOR_SELECTOR_HEIGHT);
      newColor.setMinHeight(COLOR_SELECTOR_HEIGHT);
      String hex = myResources.getString(identifier);
      setButtonFromResourceResult(newColor, hex);

      myHolder.getChildren().add(newColor);
    }
  }

  protected void setButtonFromResourceResult(Button newColor, String hex) {
    newColor.setStyle(DEFAULT_BACKGROUND_SETTER + hex);
    newColor.setOnAction(e -> changeSomething(hex));
  }


  public void changeSomething(String hex)
  {
    System.out.println("Here");
  }

  public Node getView()
  {
    return myHolder;
  }
}
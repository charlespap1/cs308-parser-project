package slogo.view.commonCommands;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.SetupScreen;
import slogo.view.StaticViewElement;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Provides generic way to put common phrases into boxes for displaying
 * @author Juliet
 */
public abstract class CommandPanel implements StaticViewElement {
  public static final int HOLDER_PADDING = 10;
  public static final String RESOURCES = "resources";
  public static final String RESOURCE_PACKAGE = RESOURCES + ".commands.";
  public static final int VBOX_SPACING = 10;

  private ResourceBundle myResources;
  private VBox myHolder;
  private List<String> myIdentifiers;
  private Text myTitle = new Text();

  public CommandPanel(String resourceTextFile, List<String> identifiers, double x, double y) {
    String myPackage = RESOURCE_PACKAGE + resourceTextFile;
    myResources = ResourceBundle.getBundle(myPackage);
    myIdentifiers = identifiers;

    myHolder = new VBox(VBOX_SPACING);
    setHolderLayout(x, y);
    fillCommands();
  }

  public Node getView()
  {
    return myHolder;
  }

  public void setTitleProperty(StringProperty sp)
  {
    myTitle.textProperty().bind(sp);
    myHolder.getChildren().add(0, myTitle);
  }

  private void setHolderLayout(double x, double y) {
    myHolder.setMinWidth(SetupScreen.WIDTH/4.0);
    myHolder.setMaxWidth(SetupScreen.WIDTH/4.0);
    myHolder.setLayoutX(x + HOLDER_PADDING);
    myHolder.setLayoutY(y + HOLDER_PADDING);
  }

  private void fillCommands() {
    for(String comm: myIdentifiers){
      String command = myResources.getString(comm);
      myHolder.getChildren().add(new Text(command));
    }
  }
}

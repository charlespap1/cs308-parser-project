package slogo.view.commonCommands;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.SetupScreen;

/**
 * Provides generic way to put common phrases into boxes for displaying
 * @author Juliet
 */
public abstract class CommandPanel {
  public static final int HOLDER_PADDING = 10;
  public static final String RESOURCES = "resources";
  public static final String RESOURCE_PACKAGE = RESOURCES + ".commands.";
  public static final int VBOX_SPACING = 10;

  private ResourceBundle myResources;
  private VBox myHolder;
  private List<String> myIdentifiers;

  public CommandPanel(String resourceTextFile, List<String> identifiers, String title, double x, double y) {
    String myPackage = RESOURCE_PACKAGE + resourceTextFile;
    Text myTitle = new Text(title);
    myResources = ResourceBundle.getBundle(myPackage);
    myIdentifiers = identifiers;

    myHolder = new VBox(VBOX_SPACING);
    myHolder.getChildren().add(myTitle);
    setHolderLayout(x, y);
    fillCommands();
  }

  public Node getView()
  {
    return myHolder;
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

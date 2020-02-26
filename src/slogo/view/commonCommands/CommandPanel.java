package slogo.view.commonCommands;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.SetupScreen;

public class CommandPanel extends Object {
  public static final int HOLDER_PADDING = 10;
  public static final String RESOURCES = "resources";
  public static final String RESOURCE_PACKAGE = RESOURCES + ".commands.";
  public static final int VBOX_SPACING = 10;

  private ResourceBundle myResources;

  private String myPackage;
  private VBox myHolder;
  private Text myTitle;
  private List<String> myIdentifiers;

  public CommandPanel(String resourceTextFile, List<String> identifiers, String title, double x, double y)
  {
    myPackage = RESOURCE_PACKAGE + resourceTextFile;
    myTitle = new Text(title);
    myResources = ResourceBundle.getBundle(myPackage);
    myIdentifiers = identifiers;

    myHolder = new VBox(VBOX_SPACING);
    myHolder.getChildren().add(myTitle);
    setHolderLayout();
    myHolder.setLayoutX(x + HOLDER_PADDING);
    myHolder.setLayoutY(y + HOLDER_PADDING);
    fillCommands();
  }

  private void setHolderLayout()
  {
    myHolder.setMinWidth(SetupScreen.WIDTH/3);
    myHolder.setMaxWidth(SetupScreen.WIDTH/3);
  }


  private void fillCommands()
  {
    for(String comm: myIdentifiers){
      String command = myResources.getString(comm);
      Text newCommand = new Text(command);
      myHolder.getChildren().add(newCommand);
    }
  }
  public Node getView()
  {
    return myHolder;
  }

}

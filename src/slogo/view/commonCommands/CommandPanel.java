package slogo.view.commonCommands;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CommandPanel extends Object {
  private static final String RESOURCES = "resources";
  public static final String RESOURCE_PACKAGE = RESOURCES + ".commands.";
  public static final int VBOX_SPACING = 10;

  private ResourceBundle myResources;

  private String myPackage;
  private VBox myHolder;
  private Text myTitle;
  private List<String> myIdentifiers;

  public CommandPanel(String resourceTextFile, List<String> identifiers, String title)
  {
    myPackage = RESOURCE_PACKAGE + resourceTextFile;
    myTitle = new Text(title);
    myResources = ResourceBundle.getBundle(myPackage);
    myIdentifiers = identifiers;

    myHolder = new VBox(VBOX_SPACING);
    myHolder.getChildren().add(myTitle);
    fillCommands();
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

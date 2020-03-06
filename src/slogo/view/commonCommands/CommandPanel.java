package slogo.view.commonCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.setup.SetupScreen;
import slogo.view.StaticViewElement;

/**
 * Provides generic way to put common phrases into boxes for displaying
 * @author Juliet
 */
public class CommandPanel implements StaticViewElement {
  public static final int HOLDER_PADDING = 10;
  public static final int SCROLLER_HEIGHT = SetupScreen.WIDTH - 400;
  public static final int NUM_PANELS = 4;
  public static final String TAB = "     ";
  public static final String RESOURCES = "resources";
  public static final String RESOURCE_PACKAGE = RESOURCES + ".commands.";
  public static final int VBOX_SPACING = 10;

  private ResourceBundle myResources;
  private VBox myHolder;
  private Text myTitle = new Text();
  private ScrollPane myView = new ScrollPane();
  private List<String> myIdentifiers;

  public CommandPanel(String resourceTextFile, String [] identifiers, double x, double y) {
    String myPackage = RESOURCE_PACKAGE + resourceTextFile;
    myResources = ResourceBundle.getBundle(myPackage);

    myIdentifiers = new ArrayList<>(Arrays.asList(identifiers));
    myHolder = new VBox(VBOX_SPACING);
    setHolderLayout(x, y);
    fillCommands();
    myHolder.getChildren().add(myView);
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
    myHolder.setMinWidth(SetupScreen.WIDTH/NUM_PANELS - HOLDER_PADDING);
    myHolder.setMaxWidth(SetupScreen.WIDTH/NUM_PANELS- HOLDER_PADDING);
    myHolder.setMaxHeight(SCROLLER_HEIGHT);
    myHolder.setLayoutX(x + HOLDER_PADDING);
    myHolder.setLayoutY(y + HOLDER_PADDING);
  }

  private void fillCommands() {
    VBox textHolder = new VBox(VBOX_SPACING);
    Text topLine = new Text();
    textHolder.getChildren().add(topLine);

    for(String comm: myIdentifiers){
      String command = myResources.getString(comm);
      textHolder.getChildren().add(new Text(TAB+ command));
    }
    Text bottomLine = new Text();
    textHolder.getChildren().add(bottomLine);
    myView.setContent(textHolder);
  }
}

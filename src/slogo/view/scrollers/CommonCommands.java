package slogo.view.scrollers;

import java.util.ResourceBundle;
import javafx.scene.text.Text;
import slogo.view.scrollers.ScrollingWindow;

/**
 * This class creates a spot on the
 * GUI which displays common commands
 * @author Juliet
 */
public class CommonCommands extends ScrollingWindow {

  public static final String TITLE = "Helpful Commands: ";
  private static final String RESOURCES = "resources";
  public static final String COMMAND_RESOURCE_PACKAGE = RESOURCES + ".commands.CommonCommands";
  public static final String [] COMMON_COMMANDS = new String[]{"Forward", "Back", "Left", "Right", "Sethheading", "Towards",
      "Set", "Pendown", "Penup", "Show", "Hide", "Home", "Clear"};


  private ResourceBundle myResources;

  public CommonCommands(double elementWidthFactor, double topPadding)
  {
    super(elementWidthFactor, topPadding, TITLE);

    setCommands();

  }

  private void setCommands()
  {
    myResources = ResourceBundle.getBundle(COMMAND_RESOURCE_PACKAGE);
    for(int i = 0; i < COMMON_COMMANDS.length; i++)
    {
      String newCommand = String.format("   %s", myResources.getString(COMMON_COMMANDS[i]));
      Text textCommand = new Text(newCommand);
      myTextHolder.getChildren().add(textCommand);
    }

  }

}

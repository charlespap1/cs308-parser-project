package slogo.view;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class creates a spot on the
 * GUI which displayes common commands
 * @author Juliet
 */
public class CommonCommands {

  public static final double COMM_COMM_SIDE_PADDING = DrawingCanvas.CANVAS_SIDE_PADDING;
  public static final double COMM_COMM_TOP_PADDING = DrawingCanvas.CANVAS_TOP_PADDING;
  public static final double COMM_COMM_MIDDLE_PADDING = 10;
  public static final String TITLE = "Helpful Commands: ";
  private static final String RESOURCES = "resources";
  public static final String COMMAND_RESOURCE_PACKAGE = RESOURCES + ".commands.CommonCommands";
  public static final String [] COMMON_COMMANDS = new String[]{"Forward", "Back", "Left", "Right", "Sethheading", "Towards",
      "Set", "Pendown", "Penup", "Show", "Hide", "Home", "Clear"};

  private VBox myHolder;
  private double commCommandWidth;
  private double commCommandHeight;
  private Text myTitle;
  private VBox myCommands;
  private ScrollPane myScroller;

  private ResourceBundle myResources;

  public CommonCommands(double screenWidth, double screenHeight)
  {

    myHolder = new VBox(10);
    commCommandWidth = screenWidth/3 - 2*COMM_COMM_SIDE_PADDING;
    commCommandHeight = screenHeight/2 - COMM_COMM_MIDDLE_PADDING - COMM_COMM_TOP_PADDING;
    myHolder.setLayoutX(screenWidth/3 + DrawingCanvas.CANVAS_SIDE_PADDING);
    myHolder.setLayoutY(COMM_COMM_TOP_PADDING);

    myCommands = new VBox(5);
    myTitle = new Text(TITLE);

    myHolder.getChildren().add(myTitle);

    myScroller = new ScrollPane();
    myScroller.setContent(myCommands);

    myHolder.setMinHeight(commCommandHeight);
    myHolder.setMaxHeight(commCommandHeight);
    myScroller.setMaxWidth(commCommandWidth);
    myScroller.setMinWidth(commCommandWidth);

    myHolder.getChildren().add(myScroller);

    setCommands();

  }

  private void setCommands()
  {
    myResources = ResourceBundle.getBundle(COMMAND_RESOURCE_PACKAGE);
    for(int i = 0; i < COMMON_COMMANDS.length; i++)
    {
      String newCommand = String.format("   %s", myResources.getString(COMMON_COMMANDS[i]));
      Text textCommand = new Text(newCommand);
      myCommands.getChildren().add(textCommand);
    }

  }


  /**
   * Allows common commands to be displayed
   * in the setup game
   * @return
   */
  public Node getView()
  {
    return myHolder;
  }

}

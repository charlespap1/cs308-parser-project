package slogo.view.commonCommands;

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * This holds the Scene of the Common Commands page as well as gives the ability to jump
 * back to your workspace or jump to a link with more information on the commands
 * @author Juliet
 */
public class CommonCommands {
  // TODO: hard coded text
  public static final String COMMON_COMMAND_TITLE = "Common Commands";
  public static final String BACK_BUTTON_TEXT = "Back";
  public static final double BUTTON_PADDING = 10;
  public static final double TOP_PADDING = 30 + BUTTON_PADDING;
  private static final double HYPERLINK_PADDING = 150;

  private Scene myPrevious;
  private Stage myStage;
  private String previousTitle;
  private double width;
  private double height;
  private Paint background;
  private Button backButton = new Button(BACK_BUTTON_TEXT);
  private StringProperty language = new SimpleStringProperty();

  public CommonCommands(Stage primaryStage, Scene previousScene, StringProperty languageProperty) {
    myStage = primaryStage;
    myPrevious = previousScene;
    previousTitle = myStage.getTitle();

    width = previousScene.getWidth();
    height = previousScene.getHeight();
    background = previousScene.getFill();
    language.bind(languageProperty);

    backButton.setLayoutX(BUTTON_PADDING);
    backButton.setLayoutY(BUTTON_PADDING);
    backButton.setOnAction(e -> showPreviousScene());
  }

  /**
   * This creates the scene to see the common commands.
   * This will be called when the see common commands button on
   * the workspace is clicked
   */
  public void showCommonCommandScene() {
    myStage.setScene(setupCommandScene());
    myStage.setTitle(COMMON_COMMAND_TITLE);
    myStage.show();
  }

  /**
   * Uses the saved data from the previous scene to
   * load back your workspace
   */
  public void showPreviousScene() {
    myStage.setScene(myPrevious);
    myStage.setTitle(previousTitle);
    myStage.show();
  }


  private Scene setupCommandScene() {
    Group myRoot = new Group();
    TurtleCommandPanel turtleCommands = new TurtleCommandPanel(language.get(), 0);
    TurtleQueriesPanel turtleQueries = new TurtleQueriesPanel(language.get(), width/4);
    MathOperationsPanel mathOps = new MathOperationsPanel(language.get(), width/2);
    BooleanOperationsPanel boolOps = new BooleanOperationsPanel(language.get(), 3*width/4);

    setHyperlink(myRoot);

    myRoot.getChildren().addAll(turtleCommands.getView(), turtleQueries.getView(), mathOps.getView(), boolOps.getView(), backButton);
    return new Scene(myRoot, width, height, background);
  }


  private void setHyperlink(Group root)
  {
    HBox centerText = new HBox();
    centerText.setAlignment(Pos.CENTER);

    Hyperlink link = new Hyperlink();
    link.setText("Click Here For More Information on Commands");
    link.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String url_open ="https://www2.cs.duke.edu/courses/spring20/compsci308/assign/03_parser/commands.php";
        try {
          java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });

    centerText.getChildren().add(link);
    centerText.setLayoutX(width/2 - HYPERLINK_PADDING);
    root.getChildren().add(centerText);
  }
}

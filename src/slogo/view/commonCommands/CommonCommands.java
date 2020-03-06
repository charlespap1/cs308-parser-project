package slogo.view.commonCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.view.LanguageHelper;

import java.io.IOException;

/**
 * This holds the Scene of the Common Commands page as well as gives the ability to jump
 * back to your workspace or jump to a link with more information on the commands
 * @author Juliet
 */
public class CommonCommands {
  // TODO: hard coded text
  public static final String COMMON_COMMAND_TITLE = "Common Commands";
  public static final String BACK_BUTTON_KEY = "CommonCommandBackButton";
  public static final String HYPERLINK_TEXT_KEY = "HyperlinkText";
  public static final String MATH_OPS_KEY = "MathOperationsText";
  public static final String BOOLEAN_OPS_KEY = "BooleanOperationsText";
  public static final String QUERIES_KEY = "TurtleQueriesText";
  public static final String COMMANDS_KEY = "TurtleCommandsText";

  public static final double BUTTON_PADDING = 10;
  public static final double TOP_PADDING = 30 + BUTTON_PADDING;
  private static final double HYPERLINK_PADDING = 150;
  public static final String MAIN_STYLESHEET = "main.css";
  public static final String LINK_TO_ALL_COMMANDS = "https://www2.cs.duke.edu/courses/spring20/compsci308/assign/03_parser/commands.php";
  private static final List<String> KEYS = new ArrayList<>(Arrays.asList(MATH_OPS_KEY, BOOLEAN_OPS_KEY, QUERIES_KEY, COMMANDS_KEY));
  private static final String RESOURCE_BUNDLE = "resources.commands.CommandSorter";

  private Scene myPrevious;
  private Stage myStage;
  private String previousTitle;
  private double width;
  private double height;
  private Paint background;
  private Button backButton = new Button();
  private StringProperty language = new SimpleStringProperty();
  private Text myTitle = new Text();
  private LanguageHelper myLanguageHelper;
  private Hyperlink myLink;
  private ResourceBundle myResource = ResourceBundle.getBundle(RESOURCE_BUNDLE);

  public CommonCommands(Stage primaryStage, Scene previousScene, StringProperty languageProperty) {
    myStage = primaryStage;
    myPrevious = previousScene;
    previousTitle = myStage.getTitle();
    myLanguageHelper = new LanguageHelper(languageProperty);

    width = previousScene.getWidth();
    height = previousScene.getHeight();
    background = previousScene.getFill();
    language.bind(languageProperty);

    backButton.textProperty().bind(myLanguageHelper.getStringProperty(BACK_BUTTON_KEY));
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

  /**
   * Allows us to set the hyperlink text based on the current language
   * @param sp
   */

  public void setHyperlinkText(StringProperty  sp){
    myTitle.textProperty().bind(sp);
    myLink.setText(myTitle.textProperty().get());

  }

  private Scene setupCommandScene() {
    Group myRoot = new Group();

    for(int i = 0; i < KEYS.size(); i ++)
    {
      String [] identifiers = myResource.getString(KEYS.get(i)).split("\\.");
      CommandPanel panel = new CommandPanel(language.get(), identifiers,i*width/4, CommonCommands.TOP_PADDING);
      panel.setTitleProperty(myLanguageHelper.getStringProperty(COMMANDS_KEY));
      myRoot.getChildren().add(panel.getView());
    }

    setHyperlink(myRoot);
    myRoot.getChildren().add(backButton);
    Scene scene = new Scene(myRoot, width, height, background);
    scene.getStylesheets().add(getClass().getClassLoader().getResource(MAIN_STYLESHEET).toExternalForm());

    return scene;
  }


  private void setHyperlink(Group root)
  {
    HBox centerText = new HBox();
    centerText.setAlignment(Pos.CENTER);

    myLink = new Hyperlink();
    setHyperlinkText(myLanguageHelper.getStringProperty(HYPERLINK_TEXT_KEY));
    myLink.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String url_open = LINK_TO_ALL_COMMANDS;
        try {
          java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
        } catch (IOException ex) {
          System.out.println("Error occurred.");
        }
      }
    });

    centerText.getChildren().add(myLink);
    centerText.setLayoutX(width/2 - HYPERLINK_PADDING);
    centerText.setLayoutY(BUTTON_PADDING);
    root.getChildren().add(centerText);
  }
}

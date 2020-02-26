package slogo.view.commonCommands;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class CommonCommands {
  // TODO: hard coded text
  public static final String COMMON_COMMAND_TITLE = "Common Commands";
  public static final String BACK_BUTTON_TEXT = "Back";
  public static final double BUTTON_PADDING = 10;
  public static final double TOP_PADDING = 30 + BUTTON_PADDING;

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

  public Scene setupCommandScene() {
    Group myRoot = new Group();
    TurtleCommandPanel turtleCommands = new TurtleCommandPanel(language.get(), 0);
    TurtleQueriesPanel turtleQueries = new TurtleQueriesPanel(language.get(), width/4);
    MathOperationsPanel mathOps = new MathOperationsPanel(language.get(), width/2);
    BooleanOperationsPanel boolOps = new BooleanOperationsPanel(language.get(), 3*width/4);

    myRoot.getChildren().addAll(turtleCommands.getView(), turtleQueries.getView(), mathOps.getView(), boolOps.getView(), backButton);
    return new Scene(myRoot, width, height, background);
  }

  public void showCommonCommandScene() {
    myStage.setScene(setupCommandScene());
    myStage.setTitle(COMMON_COMMAND_TITLE);
    myStage.show();
  }

  public void showPreviousScene() {
    myStage.setScene(myPrevious);
    myStage.setTitle(previousTitle);
    myStage.show();
  }
}

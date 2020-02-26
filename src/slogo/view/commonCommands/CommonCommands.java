package slogo.view.commonCommands;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class CommonCommands {
  public static final String COMMON_COMMAND_TITLE = "Common Commands";
  public static final double BUTTON_PADDING = 10;
  public static final double TOP_PADDING = 30 + BUTTON_PADDING;

  private Scene myPrevious;
  private Stage myStage;
  private Scene myScene;

  private String previousTitle;
  private double width;
  private double height;
  private Paint background;

  private Group myRoot;


  public CommonCommands(Stage primaryStage, Scene previousScene) {
    myStage = primaryStage;
    myPrevious = previousScene;
    previousTitle = myStage.getTitle();

    width = previousScene.getWidth();
    height = previousScene.getHeight();
    background = previousScene.getFill();
  }

  public Scene setupCommandScene()
  {
    myRoot = new Group();
    TurtleCommandPanel turtleCommands = new TurtleCommandPanel();
    TurtleQueriesPanel turtleQueries = new TurtleQueriesPanel(width/3);

    myRoot.getChildren().addAll(turtleCommands.getView(), turtleQueries.getView());

    setBackButton(myRoot);

    return new Scene(myRoot, width, height, background);
  }

  private void setBackButton(Group root)
  {
    Button backButton = new Button("Back");
    backButton.setLayoutX(BUTTON_PADDING);
    backButton.setLayoutY(BUTTON_PADDING);
    backButton.setOnAction(e -> showPreviousScene());
    root.getChildren().add(backButton);
  }


  public void showCommonCommandScene()
  {
    myScene = setupCommandScene();
    myStage.setScene(myScene);
    myStage.setTitle(COMMON_COMMAND_TITLE);
    myStage.show();
  }


  public void showPreviousScene()
  {
    myStage.setScene(myPrevious);
    myStage.setTitle(previousTitle);
    myStage.show();
  }


}

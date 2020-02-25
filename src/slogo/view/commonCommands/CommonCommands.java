package slogo.view.commonCommands;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class CommonCommands {
  public static final String COMMON_COMMAND_TITLE = "Common Commands";

  private Scene myPrevious;
  private Stage myStage;
  private Scene myScene;

  private String previousTitle;
  private double width;
  private double height;
  private Paint background;

  private Group myRoot;


  public CommonCommands(Stage primaryStage, Scene previousScene) {
    myScene = setupCommandScene();
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

    myRoot.getChildren().add(turtleCommands.getView());

    return new Scene(myRoot, width, height, background);
  }

  public void showCommonCommandScene()
  {
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

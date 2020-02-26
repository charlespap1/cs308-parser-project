package slogo.view.commonCommands;

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

    setPanels(myRoot);
    setBackButton(myRoot);
    setHyperlink(myRoot);

    return new Scene(myRoot, width, height, background);
  }

  private void setHyperlink(Group root)
  {
    HBox centerText = new HBox();
    centerText.setAlignment(Pos.CENTER);

    Hyperlink link = new Hyperlink();
    link.setText("http://example.com");
    link.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        System.out.println("This link is clicked");
      }
    });

    centerText.getChildren().add(link);

    root.getChildren().add(centerText);
  }


  private void setPanels(Group root)
  {
    String language = "English";
    TurtleCommandPanel turtleCommands = new TurtleCommandPanel(language, 0);
    TurtleQueriesPanel turtleQueries = new TurtleQueriesPanel(language, width/4);
    MathOperationsPanel mathOPs = new MathOperationsPanel(language, 2*width/4);
    BooleanOperationsPanel boolOps = new BooleanOperationsPanel(language, 3*width/4);

    root.getChildren().addAll(turtleCommands.getView(), turtleQueries.getView(), mathOPs.getView(), boolOps.getView());
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

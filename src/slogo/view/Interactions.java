package slogo.view;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.controller.ButtonAction;
import slogo.view.scrollers.HistoryCanvas;
import slogo.view.scrollers.ListViewer;

/**
 * This class holds all of the interactions between the UI objects
 */
public class Interactions implements View {
  public static final String TITLE = "SLogo";

  private SetupScreen mySetup;
  private UserCommandField myUserInput;
  private Group root;
  private Turtle myTurtle;
  private DrawingCanvas myCanvas;
  private Button myGo;
  private Button myClear;
  private Button myStop;
  private HistoryCanvas myHistory;
  private Text myCurrentErrorMessage;

  public Interactions(Stage primaryStage)
  {
    mySetup = new SetupScreen();
    Scene myScene = mySetup.setupGame();

    myGo = mySetup.getGoButton();
    myClear = mySetup.getClearButton();
    myClear.setOnAction(e -> clearCanvas());
    myStop = mySetup.getStopButton();
    myStop.setOnAction(e -> returnToDefaultTurtle());

    myTurtle = mySetup.getTurtle();
    myUserInput = mySetup.getUserInput();
    myCanvas = mySetup.getDrawingCanvas();
    myHistory = mySetup.getHistoryCanvas();
    root = mySetup.getRoot();
    myCurrentErrorMessage = mySetup.getCurrentErrorMessage();

    primaryStage.setScene(myScene);
    primaryStage.setTitle(TITLE);
    primaryStage.show();
  }

  /**
   * Method which can be called by any instance of a Visual object
   * and allows the caller to get the user input from the command input field
   * @return
   * @throws NullPointerException
   */
  @Override
  public String getInstruction() throws NullPointerException {
    return myUserInput.getUserInput();
  }

  public void setGoButton(ButtonAction goAction){
    myGo.setOnAction(e -> goAction.onClickAction());
  }

  /**
   * Updates the movement of the turtle according to new states
   */
  private void update() {
    Line newLine = myTurtle.drawLine();
    if (newLine!=null) {
      root.getChildren().add(newLine);
      myCanvas.addLine(newLine);
    }
  }

  @Override
  public void showError(String errorMessage) {
    myCurrentErrorMessage.setText(errorMessage);
  }

  @Override
  public void changeCanvasColor(Color color) {
    myCanvas.changeBackground(color);
  }

  private void clearCanvas()
  {
    root.getChildren().removeAll(myCanvas.getLines());
  }

  private void returnToDefaultTurtle()
  {
    myTurtle.returnTurtleToDefault();
    clearCanvas();
  }

  public void setProperties(slogo.model.Turtle turtle){
    myTurtle.setProperties(turtle);
    turtle.turtleYProperty().addListener((o, oldVal, newVal) -> {
      System.out.println("turtle has changed!");
      update();
      // x, y, angle, and penUp will update automatically w binding. also if we change them here, it should
      // also change the values in model.turtle since the binding is bidirectional
      // so once y changes, you know all 3 others are changed and you can draw a line (or not) and update currx and
      // curry, which exist specifically for line drawing purposes
      // DELETE THIS COMMENT LATER});
    });
    turtle.currCommandProperty().addListener((o, oldVal, newVal) -> myHistory.addHistory(newVal));
  }

  public void setViewLists(ObservableList<String> variableList, ObservableList<String> newCommandList){
    mySetup.setVariableList(variableList);
    mySetup.setNewCommandList(newCommandList);
  }
}

package slogo.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.State;
import slogo.controller.ButtonAction;
import slogo.view.scrollers.HistoryCanvas;

/**
 * This class holds all of the interactions between the UI objects
 */
public class Interactions implements View {
  public static final String TITLE = "SLogo";

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
    SetupScreen setup = new SetupScreen();
    Scene myScene = setup.setupGame();

    myGo = setup.getGoButton();
    myClear = setup.getClearButton();
    myClear.setOnAction(e -> clearCanvas());
    myStop = setup.getStopButton();
    myStop.setOnAction(e -> returnToDefaultTurtle());

    myTurtle = setup.getTurtle();
    myUserInput = setup.getUserInput();
    myCanvas = setup.getDrawingCanvas();
    myHistory = setup.getHistoryCanvas();
    root = setup.getRoot();
    myCurrentErrorMessage = setup.getCurrentErrorMessage();

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
    String input = myUserInput.getUserInput();
    if(input.equals("hi"))
    {
      //there is now a listener so that when the ylocation of the turtle changes, update in this class will be called
      State newState = new State(50, 50, false, 0);
      //updateDisplay(newState);
      newState = new State(50, 30, false, 0);
      //updateDisplay(newState);
      newState = new State(0, -100, false, 0);
      //updateDisplay(newState);
      newState = new State(-200, -200, false, -90);
      //updateDisplay(newState);
    }
    else if(input.equals("bye"))
    {
      showError("you typed bye");
    }
    else if(input.equals("nope"))
    {
      showError("that's wrong");
    }
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
    root.getChildren().add(newLine);
    myCanvas.addLine(newLine);

    //add stuff 5 times to see scrolling effect
    for(int i = 0; i < 5; i++)
    {
      String command = myTurtle.getCommand();
      myHistory.addHistory(command);
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
      // DELETE THIS COMMENT LATER
    });
  }
}

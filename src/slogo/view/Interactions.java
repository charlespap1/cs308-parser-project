package slogo.view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import slogo.State;
import slogo.view.scrollers.HistoryCanvas;

/**
 * This class holds all of the interactions between the UI objects
 */
public class Interactions implements View{

  private UserCommandField myUserInput;
  private Group root;
  private Turtle myTurtle;
  private DrawingCanvas myCanvas;
  private Button myGo;
  private Button myClear;
  private Button myStop;
  private HistoryCanvas myHistory;
  private Text myCurrentErrorMessage;

  public Interactions(SetupScreen setup)
  {
    myGo = setup.getGoButton();
    myGo.setOnAction(e -> getInstruction());

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
      State newState = new State(50, 50, false, 0);
      updateDisplay(newState);
      newState = new State(50, 30, false, 0);
      updateDisplay(newState);
      newState = new State(0, -100, false, 0);
      updateDisplay(newState);
      newState = new State(-200, -200, false, -90);
      updateDisplay(newState);
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

  /**
   * Updates the movement of the turtle according to new states
   * @param nextState
   */
  @Override
  public void updateDisplay(State nextState) {
    Line newLine = myTurtle.update(nextState);
    root.getChildren().add(newLine);
    root.getChildren().remove(myTurtle.getView());
    root.getChildren().add(myTurtle.getView());
    myCanvas.addLine(newLine);

    //add stuff 5 times to see scrolling effect
    for(int i = 0; i < 5; i++)
    {
      String command = nextState.toString();
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
}

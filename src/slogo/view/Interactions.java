package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * This class holds all of the interactions between the UI objects
 */
public class Interactions implements View {
  public static final String TITLE = "SLogo";

  private SetupScreen mySetup;
  private Group root;
  private Turtle myTurtle;
  private DrawingCanvas myCanvas;

  public Interactions(Stage primaryStage)
  {
    mySetup = new SetupScreen();
    Scene myScene = mySetup.setupGame();

    mySetup.setBelowCanvasButtons(e -> returnToDefaultTurtle(), e -> clearCanvas());

    myTurtle = mySetup.getTurtle();
    root = mySetup.getRoot();
    myCanvas = mySetup.getDrawingCanvas();

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
    return mySetup.getUserInput();
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

  // TODO: we probably don't need this publicly here, as it will all be front end
  @Override
  public void changeCanvasColor(Color color) {
    myCanvas.changeBackground(color);
  }

  private void clearCanvas() {
    root.getChildren().removeAll(myCanvas.getLines());
  }

  private void returnToDefaultTurtle()
  {
    myTurtle.returnTurtleToDefault();
    clearCanvas();
  }

  public void setTurtle(slogo.model.Turtle turtle){
    myTurtle.setProperties(turtle);
    turtle.turtleYProperty().addListener((o, oldVal, newVal) -> update());
    turtle.currCommandProperty().addListener((o, oldVal, newVal) -> mySetup.addHistory(newVal));
  }

  public void setGoButton(EventHandler<ActionEvent> goAction){
    mySetup.setGoButton(goAction);
  }

  public void setViewLists(ObservableList<String> variableList, ObservableList<String> newCommandList){
    mySetup.setVariableList(variableList);
    mySetup.setNewCommandList(newCommandList);
  }

  public void setErrorMessage(StringProperty error){
    mySetup.bindErrorMessage(error);
  }


}

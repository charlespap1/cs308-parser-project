package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import slogo.view.commonCommands.CommonCommands;


/**
 * This class holds all of the interactions between the UI objects
 * @author Juliet, Natalie
 */
public class Interactions implements View {
  public static final String TITLE = "SLogo";

  private SetupScreen mySetup;
  private Group root;
  private Turtle myTurtle;
  private DrawingCanvas myCanvas;

  public Interactions(Stage primaryStage) {
    mySetup = new SetupScreen();
    Scene myScene = mySetup.setupGame();
    CommonCommands myCommonCommands = new CommonCommands(primaryStage, myScene, getLanguageChoice());
    mySetup.addCommonCommands(myCommonCommands);
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
  public String getInstruction() throws NullPointerException {
    //TODO: is this all the error handling we need for this?
    return mySetup.getUserInput();
  }

  /**
   * Sets the frontend turtle whenever the location is changed
   * in the backend
   * @param turtle
   */
  public void setTurtle(slogo.model.Turtle turtle){
    myTurtle.setProperties(turtle);
    turtle.pointProperty().addListener((o, oldVal, newVal) -> update());
    turtle.currCommandProperty().addListener((o, oldVal, newVal) -> mySetup.addHistory(newVal));
  }

  /**
   * Gives the ListViewers their updated list values (binding)
   * @param variableList
   * @param newCommandList
   */
  public void setViewLists(ObservableList<String> variableList, ObservableList<String> newCommandList){
    mySetup.setVariableList(variableList);
    mySetup.setNewCommandList(newCommandList);
  }

  /**
   * Sets the go button to be binded to do action in the backend
   * @param goAction
   */
  public void setGoButton(EventHandler<ActionEvent> goAction){ mySetup.setGoButton(goAction); }

  public void setNewWindowButton(EventHandler<ActionEvent> newWindowAction) { mySetup.setNewWindowButton(newWindowAction); }

  public void setErrorMessage(StringProperty error){ mySetup.bindErrorMessage(error); }

  public StringProperty getLanguageChoice() { return mySetup.getLanguageChoice(); }
  
  public ClearAction getClearAction() { return this::clearCanvas; }

  /**
   * Updates the movement of the turtle according to new states
   */
  private void update() {
    Line newLine = myTurtle.drawLineAndBound();
    if (newLine!=null) {
      root.getChildren().add(newLine);
      myCanvas.addLine(newLine);
      myTurtle.getView().toFront();
    }
  }

  private void returnToDefaultTurtle() {
    myTurtle.returnTurtleToDefault();
    clearCanvas();
  }

  private void clearCanvas() {
    mySetup.clearHistory();
    root.getChildren().removeAll(myCanvas.getLines());
  }
}

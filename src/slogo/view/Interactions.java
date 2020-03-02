package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import slogo.view.commonCommands.CommonCommands;

import java.util.List;
import java.util.Objects;


/**
 * This class holds all of the interactions between the UI objects
 * @author Juliet, Natalie
 */
public class Interactions implements View {
  public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";
  public static final String TITLE = "SLogo";

  private SetupScreen mySetup;
  private Group root;
  //private Turtle myTurtle;
  private List<Turtle> myTurtles;
  private DrawingCanvas myCanvas;

  public Interactions(Stage primaryStage) {
    mySetup = new SetupScreen();
    Scene myScene = mySetup.setupGame();
    CommonCommands myCommonCommands = new CommonCommands(primaryStage, myScene, getLanguageChoice());
    mySetup.addCommonCommands(myCommonCommands);
    mySetup.setBelowCanvasButtons(e -> returnToDefaultTurtle(), e -> clearCanvas());
//    myTurtle = mySetup.getTurtle();
    myTurtles = mySetup.getTurtles();
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

//  /**
//   * Sets the frontend turtle whenever the location is changed
//   * in the backend
//   * @param turtle
//   */
//  public void setTurtle(slogo.model.Turtle turtle){
//    myTurtle.setProperties(turtle);
//    turtle.pointProperty().addListener((o, oldVal, newVal) -> update());
//    turtle.currCommandProperty().addListener((o, oldVal, newVal) -> mySetup.addHistory(newVal));
//  }
  public void setTurtle(slogo.model.Turtle turtle){
    //does nothing; just here to satisfy requirements of 'view' interface
  }

  /**
   * Sets the list of frontend turtles whenever the location of a backend turtle is changed
   * in the backend
   * @param turtles: a list of turtles given from the backend
   */
  public void setTurtles(List<slogo.model.Turtle> turtles){
    for (int i=0; i<turtles.size(); i++){
      if(turtles.get(i) == null) continue;
      Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
      if(i>=myTurtles.size()) myTurtles.add(new Turtle(image,0,0)); //besides the image, properties don't matter on this line
      myTurtles.get(i).setProperties(turtles.get(i));
      turtles.get(i).pointProperty().addListener((o, oldVal, newVal) -> update());
      turtles.get(i).currCommandProperty().addListener((o, oldVal, newVal) -> mySetup.addHistory(newVal));
    }
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

//  /**
//   * Updates the movement of the turtle according to new states
//   */
//  private void update() {
//    Line newLine = myTurtle.drawLineAndBound();
//    if (newLine!=null) {
//      root.getChildren().add(newLine);
//      myCanvas.addLine(newLine);
//      myTurtle.getView().toFront();
//    }
//  }

  /**
   * Updates the movement of all of the turtles according to new states
   */
  private void update() {
    for(Turtle t: myTurtles) {
      Line newLine = t.drawLineAndBound();
      if (newLine != null) {
        root.getChildren().add(newLine);
        myCanvas.addLine(newLine);
        t.getView().toFront();
      }
    }
  }

//  private void returnToDefaultTurtle() {
//    myTurtle.returnTurtleToDefault();
//    clearCanvas();
//  }

  private void returnToDefaultTurtle() {
    for (int i=1; i<myTurtles.size(); i++) myTurtles.remove(i);
    myTurtles.get(0).returnTurtleToDefault();
    clearCanvas();
  }

  private void clearCanvas() {
    mySetup.clearHistory();
    root.getChildren().removeAll(myCanvas.getLines());
  }
}

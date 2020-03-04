package slogo.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import slogo.model.code.Token;
import slogo.view.commonCommands.CommonCommands;

import java.lang.reflect.Method;


/**
 * This class holds all of the interactions between the UI objects
 * @author Juliet, Natalie
 */
public class Interactions implements View {
  public static final String TITLE = "SLogo";
  public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";

  private SetupScreen mySetup;
  private Group root;
  private List<Turtle> myTurtles = new ArrayList<>();
  private Turtle myInitialTurtle;
  private DrawingCanvas myCanvas;
  private String myPreferences;

  public Interactions(Stage primaryStage, String preferences) {
    mySetup = new SetupScreen();
    Scene myScene = mySetup.setupGame();
    CommonCommands myCommonCommands = new CommonCommands(primaryStage, myScene, getLanguageChoice());
    mySetup.addCommonCommands(myCommonCommands);
    myInitialTurtle = mySetup.getInitialTurtle();
    myTurtles.add(myInitialTurtle);
    root = mySetup.getRoot();
    myCanvas = mySetup.getDrawingCanvas();
    myPreferences = preferences;

    primaryStage.setScene(myScene);
    primaryStage.setTitle(TITLE);
    primaryStage.show();
    setPreferences();
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
  public void setInitialTurtle(slogo.model.Turtle turtle){
    myInitialTurtle.setProperties(turtle);
    turtle.pointProperty().addListener((o, oldVal, newVal) -> update(myInitialTurtle));
    turtle.currCommandProperty().addListener((o, oldVal, newVal) -> mySetup.addHistory(newVal));
  }

  public void addTurtle(slogo.model.Turtle turtle){
    Turtle newTurtle = mySetup.addNewTurtle();
    myTurtles.add(newTurtle);
    newTurtle.setProperties(turtle);
    turtle.pointProperty().addListener((o, oldVal, newVal) -> update(newTurtle));
    //turtle.currCommandProperty().addListener((o, oldVal, newVal) -> mySetup.addHistory(newVal));
  }

  /**
   * Gives the ListViewers their updated list values (binding)
   * @param variableList
   * @param newCommandList
   */
  public void setViewLists(ObservableList<Token> variableList, ObservableList<Token> newCommandList){
    mySetup.setVariableList(variableList);
    mySetup.setNewCommandList(newCommandList);
  }

  /**
   * Sets the go button to be binded to do action in the backend
   * @param goAction
   */
  public void setGoButton(EventHandler<ActionEvent> goAction){ mySetup.setGoButton(goAction); }

  public void setNewWindowButton(EventHandler<ActionEvent> newWindowAction) { mySetup.setNewWindowButton(newWindowAction); }
  public void setNewConfigButton(EventHandler<ActionEvent> newWindowAction) { mySetup.setNewConfigButton(newWindowAction); }

  public void setErrorMessage(StringProperty error){ mySetup.bindErrorMessage(error); }

  public StringProperty getLanguageChoice() { return mySetup.getLanguageChoice(); }

  public DisplayAction getAction(String methodName) {
    return new DisplayAction() {
      @Override
      public int execute(List<Double> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = SetupScreen.class.getDeclaredMethod(methodName, List.class);
        Object value = m.invoke(mySetup, params);
        return (Integer) value;
      }
    };
  }

  /**
   * Updates the movement of the turtle according to new states
   */
  private void update(Turtle newTurtle) {
    Line newLine = newTurtle.drawLineAndBound();
    if (newLine!=null) {
      root.getChildren().add(newLine);
      myCanvas.addLine(newLine);
      newTurtle.getView().toFront();
    }
  }

  public void setPreferences()
  {
    mySetup.setPreferences(myPreferences);
  }


  public void setPopupButton(EventHandler<ActionEvent> showPopup) {
    mySetup.setNewConfigButton(showPopup);
  }

}

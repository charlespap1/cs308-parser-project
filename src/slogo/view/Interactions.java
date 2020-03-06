package slogo.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.controller.DirectExecutor;
import slogo.model.tokens.Token;
import slogo.view.popup.TurtleStatePopup;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import slogo.view.setup.ScreenManager;
import slogo.view.setup.SetupScreen;


/**
 * This class holds all of the interactions between the UI objects
 * @author Juliet, Natalie
 */
public class Interactions implements View {
  public static final String TITLE = "SLogo";
  public TurtleStatePopup myTurtleStatePopup;

  private ScreenManager myScreen;
  private SetupScreen mySetup;

  public Interactions(Stage primaryStage) {
    mySetup = new SetupScreen();
    Scene myScene = mySetup.setupGame();
    mySetup.addCommonCommands(primaryStage, myScene);
    myTurtleStatePopup = mySetup.getTurtleStatePopup();
    myScreen = mySetup.getScreenManager();

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
    return myScreen.getUserInput();
  }

  public File getFile(){
    return mySetup.getFile();
  }

  public void setPreferences(String preferences) {
    myScreen.setPreferences(preferences);
  }

  public String getNewWindowPreferences(){
    return mySetup.getNewWindowPreferences();
  }

  /**
   * Sets the frontend turtle whenever the location is changed
   * in the backend
   * @param turtle
   */
  public void addTurtle(slogo.model.Turtle turtle){
    myScreen.addNewTurtle(turtle);
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

  public void setupHistory(ObservableList<Token> historyList, BooleanProperty undoDisabled, BooleanProperty redoDisabled){
    mySetup.setupHistory(historyList, undoDisabled, redoDisabled);
  }

  /**
   * Sets the go button to be binded to do action in the backend
   * @param goAction
   */
  public void setGoButton(EventHandler<ActionEvent> goAction){ mySetup.setGoButton(goAction); }
  public void setNewWindowButton(EventHandler<ActionEvent> newWindowAction, Stage stage) { mySetup.setNewWindowButton(newWindowAction, stage); }
  public void setLoadTextFileButton(EventHandler<ActionEvent> newWindowAction, Stage stage) { mySetup.setLoadTextFileButton(newWindowAction, stage); }
  public void setTurtlesStateButton(EventHandler<ActionEvent> showTurtlesAction) { mySetup.setTurtlesStatesButton(showTurtlesAction); }

  public void setUndoAction(EventHandler<ActionEvent> undoAction) { mySetup.setUndoButton(undoAction); }
  public void setRedoAction(EventHandler<ActionEvent> redoAction) { mySetup.setRedoButton(redoAction); }

  public void setErrorMessage(StringProperty error){ mySetup.bindErrorMessage(error); }
  public void setClearHistory(EventHandler<ActionEvent> clearAction) { mySetup.setClearHistory(clearAction); }

  public StringProperty getLanguageChoice() { return myScreen.getLanguageChoice(); }

  public DisplayAction getAction(String methodName) {
    return params -> {
      try {
        Method m = ScreenManager.class.getDeclaredMethod(methodName, List.class);
        Object value = m.invoke(myScreen, params);
        return (Integer) value;
      } catch (Exception e) {
        //TODO: errors
        System.out.println("bad method");
        return 0;
      }
    };
  }

  public void setDirectInstructionExecutor(DirectExecutor executor) { mySetup.setDirectExecutor(executor); }
}

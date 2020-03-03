package slogo.view;

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
import slogo.view.commonCommands.CommonCommands;

import java.lang.reflect.Method;


/**
 * This class holds all of the interactions between the UI objects
 * @author Juliet, Natalie
 */
public class Interactions implements View {
  public static final String TITLE = "SLogo";

  private SetupScreen mySetup;
  private Group root;

  public Interactions(Stage primaryStage) {
    mySetup = new SetupScreen();
    Scene myScene = mySetup.setupGame();
    CommonCommands myCommonCommands = new CommonCommands(primaryStage, myScene, getLanguageChoice());
    mySetup.addCommonCommands(myCommonCommands);

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
    return mySetup.getUserInput();
  }

  /**
   * Sets the frontend turtle whenever the location is changed
   * in the backend
   * @param turtle
   */
  public void addTurtle(slogo.model.Turtle turtle){ mySetup.addNewTurtle(turtle); }

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

  public DisplayAction getAction(String methodName) {
    return params -> {
      Method m = SetupScreen.class.getDeclaredMethod(methodName, List.class);
      Object value = m.invoke(this.mySetup, params);
      return (Integer) value;
    };
  }

  public void setPopupButton(EventHandler<ActionEvent> showPopup) {
    mySetup.setPopupButton(showPopup);
  }

}

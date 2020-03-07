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
import slogo.view.exceptions.MethodDoesNotExistException;
import slogo.view.setup.ScreenManager;
import slogo.view.setup.SetupScreen;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;


/**
 * This class holds all of the interactions between the UI objects
 *
 * @author Juliet, Natalie
 */
public class Interactions implements View {
    private static final String TITLE = "SLogo";
    private ScreenManager myScreen;
    private SetupScreen mySetup;

    public Interactions(Stage primaryStage) {
        mySetup = new SetupScreen();
        Scene myScene = mySetup.setupGame();
        mySetup.addCommonCommands(primaryStage, myScene);
        myScreen = mySetup.getScreenManager();

        primaryStage.setScene(myScene);
        primaryStage.setTitle(TITLE);
        primaryStage.show();
    }

    /**
     * Method which can be called by any instance of a Visual object
     * and allows the caller to get the user input from the command input field
     *
     * @return
     * @throws NullPointerException
     */
    public String getInstruction() throws NullPointerException {
        return myScreen.getUserInput();
    }

    /**
     * Used to get the
     *
     * @return
     */
    public File getFile() {
        return mySetup.getFile();
    }

    /**
     * Sets the frontend turtle whenever the location is changed
     * in the backend
     *
     * @param turtle
     */
    public void addTurtle(slogo.model.Turtle turtle) {
        myScreen.addNewTurtle(turtle);
    }

    /**
     * Returns front end methods for Display Commands.
     *
     * @param methodName
     * @return
     */
    public DisplayAction getAction(String methodName) {
        return params -> {
            try {
                Method m = ScreenManager.class.getDeclaredMethod(methodName, List.class);
                Object value = m.invoke(myScreen, params);
                return (Integer) value;
            } catch (Exception e) {
                mySetup.setError(new MethodDoesNotExistException());
                return 0;
            }
        };
    }

    /**
     * Gives the ListViewers their updated list values (binding)
     *
     * @param variableList
     * @param newCommandList
     */
    public void setViewLists(ObservableList<Token> variableList, ObservableList<Token> newCommandList) {
        mySetup.setVariableList(variableList);
        mySetup.setNewCommandList(newCommandList);
    }

    /**
     * Links front and back end history and disabling of undo/redo buttons
     *
     * @param historyList
     * @param undoDisabled
     * @param redoDisabled
     */
    public void setupHistory(ObservableList<Token> historyList, BooleanProperty undoDisabled, BooleanProperty redoDisabled) {
        mySetup.setupHistory(historyList, undoDisabled, redoDisabled);
    }

    /**
     * Sets the go button to be bound to action in the backend
     *
     * @param goAction
     */
    public void setGoButton(EventHandler<ActionEvent> goAction) {
        mySetup.setGoButton(goAction);
    }

    public void setNewWindowButton(EventHandler<ActionEvent> newWindowAction, Stage stage) {
        mySetup.setNewWindowButton(newWindowAction, stage);
    }

    public void setLoadTextFileButton(EventHandler<ActionEvent> newWindowAction, Stage stage) {
        mySetup.setLoadTextFileButton(newWindowAction, stage);
    }

    public void setLoadVarsAndCommandsButton(EventHandler<ActionEvent> newWindowAction, Stage stage) {
        mySetup.setLoadVarsAndCommands(newWindowAction, stage);
    }

    public void setSaveTextFileButton(Stage stage) {
        mySetup.setSaveTextFileButton(stage);
    }

    public void setSaveVariableButton(String newCommands, Stage stage) {
        mySetup.setVariableSaveButton(newCommands, stage);
    }

    public void setUndoAction(EventHandler<ActionEvent> undoAction) {
        mySetup.setUndoButton(undoAction);
    }

    public void setRedoAction(EventHandler<ActionEvent> redoAction) {
        mySetup.setRedoButton(redoAction);
    }

    public void setErrorMessage(StringProperty error) {
        mySetup.bindErrorMessage(error);
    }

    public void setClearHistory(EventHandler<ActionEvent> clearAction) {
        mySetup.setClearHistory(clearAction);
    }

    public void setPreferences(String preferences) {
        myScreen.setPreferences(preferences);
    }

    public void setDirectInstructionExecutor(DirectExecutor executor) {
        mySetup.setDirectExecutor(executor);
    }

    public String getNewWindowPreferences() {
        return mySetup.getNewWindowPreferences();
    }

    public StringProperty getLanguageChoice() {
        return myScreen.getLanguageChoice();
    }
}

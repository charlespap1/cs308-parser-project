package slogo.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import slogo.controller.DirectExecutor;
import slogo.model.tokens.Token;

import java.io.File;

/**
 * This method specifies to the controller all of the methods which
 * it can call on a frontend View API
 *
 * @author Natalie, Juliet, Mike
 */

public interface View {
    String getInstruction() throws NullPointerException;

    /**
     * Delivers a file which the user specified to be executed
     * in the backend
     * @return
     */
    File getFile();

    /**
     * Needed to tell the backend which language we are in so that
     * they can use the correct resource bundle to identify instructions
     * @return
     */
    StringProperty getLanguageChoice();

    /**
     * Uses reflection to call the needed method
     * @param method
     * @return
     */
    DisplayAction getAction(String method);

    /**
     * Gets the preference the User specifies in the frontend to create
     * a new window in the controller
     * @return
     */
    String getNewWindowPreferences();

    /**
     * Connects a newly made backend turtle to a new frontend turtle
     * @param turtle
     */
    void addTurtle(slogo.model.Turtle turtle);

    /**
     * Gives the go button the action of handing the user input
     * to the backend
     * @param goAction
     */
    void setGoButton(EventHandler<ActionEvent> goAction);

    /**
     * Gives the go button of the new window popup the action of creating a
     * new window, also allows popup to be displayed on the current stage
     * @param newWindowAction
     * @param stage
     */
    void setNewWindowButton(EventHandler<ActionEvent> newWindowAction, Stage stage);

    /**
     * Gives the load text button the ability to hand off the loaded text to the backend,
     * also allows the popup to be displayed on the current stage
     * @param newWindowAction
     * @param stage
     */
    void setLoadTextFileButton(EventHandler<ActionEvent> newWindowAction, Stage stage);

    /**
     * Gives the load variables button the ability to put the loaded variables into the
     * backend, also allows popup to be displayed on the current stage
     * @param newWindowAction
     * @param stage
     */
    void setLoadVarsAndCommandsButton(EventHandler<ActionEvent> newWindowAction, Stage stage);

    /**
     * Allows the popup to be displayed on the current stage. Doesn't need any controller
     * methods so doesn't need to be passed an EventHandler
     * @param stage
     */
    void setSaveTextFileButton(Stage stage);

    /**
     * Allows undo action to occur in front an backend when you click the frontend button
     * @param undoAction
     */
    void setUndoAction(EventHandler<ActionEvent> undoAction);

    /**
     * Allows redo action to occur in front an backend when you click the frontend button
     * @param redoAction
     */
    void setRedoAction(EventHandler<ActionEvent> redoAction);

    /**
     * Allows clear action to occur in front an backend when you click the frontend button
     * @param clearAction
     */
    void setClearHistory(EventHandler<ActionEvent> clearAction);


    void setDirectInstructionExecutor(DirectExecutor executor);

    /**
     * Binds the frontend error message to the backend so both sides can throw exceptions
     * @param error
     */
    void setErrorMessage(StringProperty error);

    /**
     * Sets the preferences of the newly created window
     * @param preferences
     */
    void setPreferences(String preferences);

    /**
     * Connects the front and backend new command and variable lists
     * @param variableList
     * @param newCommandList
     */
    void setViewLists(ObservableList<Token> variableList, ObservableList<Token> newCommandList);

    /**
     * Connects the front and backend history list and allows for clicking to execute
     * @param historyList
     * @param undoDisabled
     * @param redoDisabled
     */
    void setupHistory(ObservableList<Token> historyList, BooleanProperty undoDisabled, BooleanProperty redoDisabled);
}

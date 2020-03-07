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
 * @author Natalie, Mike, Juliet
 */

public interface View {
    String getInstruction() throws NullPointerException;

    File getFile();

    StringProperty getLanguageChoice();

    DisplayAction getAction(String method);

    String getNewWindowPreferences();

    void addTurtle(slogo.model.Turtle turtle);

    void setGoButton(EventHandler<ActionEvent> goAction);

    void setNewWindowButton(EventHandler<ActionEvent> newWindowAction, Stage stage);

    void setLoadTextFileButton(EventHandler<ActionEvent> newWindowAction, Stage stage);

    void setLoadVarsAndCommandsButton(EventHandler<ActionEvent> newWindowAction, Stage stage);

    void setSaveTextFileButton(Stage stage);

    void setSaveVariableButton(String newCommands, Stage stage);

    void setUndoAction(EventHandler<ActionEvent> undoAction);

    void setRedoAction(EventHandler<ActionEvent> redoAction);

    void setClearHistory(EventHandler<ActionEvent> clearAction);

    void setDirectInstructionExecutor(DirectExecutor executor);

    void setErrorMessage(StringProperty error);

    void setPreferences(String preferences);

    void setViewLists(ObservableList<Token> variableList, ObservableList<Token> newCommandList);

    void setupHistory(ObservableList<Token> historyList, BooleanProperty undoDisabled, BooleanProperty redoDisabled);
}

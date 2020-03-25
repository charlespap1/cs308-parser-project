package slogo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import slogo.model.tokens.Instruction;
import slogo.model.tokens.Token;
import slogo.view.DisplayAction;

import java.io.File;

/**
 * @author Charles, Natalie, Michael
 * api for backend
 */
public interface ModelAPI {

    /**
     * Method that acts as entry point to backend for passing chunk of code to be run, and calls appropriate functions for parsing and handling.
     * @param rawCode String of code that will be parsed by backend
     */
    void executeCode(String rawCode);

    /**
     * Method that acts as entry point to backend for passing chunk of code to be run, and calls appropriate functions for parsing and handling.
     * @param f File object of code to be parsed and run
     */
    void executeCode(File f);

    /**
     * Method that allows passing of an instruction object from history for backend to execute.
     * @param instruction Instruction object that represents an instruction to be executed by backend
     */
    void executeCode(Instruction instruction);

    void setAction(String key, DisplayAction action);

    ObservableList<Token> getVariableList();

    ObservableList<Token> getNewCommandsList();

    ObservableList<Token> getHistoryList();

    BooleanProperty getUndoDisabled();

    BooleanProperty getRedoDisabled();

    StringProperty getErrorMessage();

    void setErrorMessage(String error);

    void clearHistory();

    void undo();

    void redo();
}

package slogo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import slogo.model.tokens.Instruction;
import slogo.model.tokens.Token;
import slogo.view.DisplayAction;

import java.io.File;

public interface ModelAPI {
    void executeCode(String rawCode);

    void executeCode(File f);

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

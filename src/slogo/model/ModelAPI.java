package slogo.model;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import slogo.model.tokens.Token;

import java.io.File;

public interface ModelAPI {
    void executeCode(String rawCode);
    void executeCode(File f);
    ObservableList<Token> getVariableList();
    ObservableList<Token> getNewCommandsList();
    StringProperty getErrorMessage();
}

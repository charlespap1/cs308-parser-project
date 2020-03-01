package slogo.model;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.io.File;

public interface ModelAPI {
    void executeCode(String rawCode);
    void executeCode(File f);
    //Turtle getTurtle();
    ObservableList<String> getVariableList();
    ObservableList<String> getNewCommandsList();
    StringProperty getErrorMessage();
}

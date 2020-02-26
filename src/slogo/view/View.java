package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import slogo.State;

public interface View {
    String getInstruction() throws NullPointerException;
    StringProperty getLanguageChoice();
    void setTurtle(slogo.model.Turtle turtle);
    void setGoButton(EventHandler<ActionEvent> goAction);
    void setViewLists(ObservableList<String> variableList, ObservableList<String> newCommandList);
    void setErrorMessage(StringProperty error);
}

package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This method specifies the controller all of the methods which
 * it can call on a frontend View API
 * @author Natalie, Mike, Juliet
 */

public interface View {
    String getInstruction() throws NullPointerException;
    StringProperty getLanguageChoice();
    DisplayAction getAction(String method);
    void addTurtle(slogo.model.Turtle turtle);
    void setGoButton(EventHandler<ActionEvent> goAction);
    void setErrorMessage(StringProperty error);
}

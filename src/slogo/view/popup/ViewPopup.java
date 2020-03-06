package slogo.view.popup;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;

public interface ViewPopup {

  void setPopupButton(EventHandler<ActionEvent> e);

  Popup getMyPopup();

  void setGoButtonProperty(StringProperty sp);

  void setPromptProperty(StringProperty sp);

}

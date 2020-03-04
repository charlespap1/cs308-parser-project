package slogo.view.popup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;

public interface ViewPopup {

  void setPopupButton(EventHandler<ActionEvent> e);

  Popup getMyPopup();

}

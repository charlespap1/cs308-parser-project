package slogo.view.scrollers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import slogo.view.SetInputAction;

import java.lang.reflect.Method;

/**
 * Allows us to condense variables and new commands into one
 * class with same constructor
 * @author Natalie
 */

public class ListViewer extends ScrollingWindow {
  private ListView<String> myList = new ListView<>();

  public ListViewer(double elementWidthFactor, double topPadding, SetInputAction inputSetter) {
    super(elementWidthFactor, topPadding);
    myList.setPrefSize(myWidth-2*VBOX_SPACING, myHeight);
    myTextHolder.getChildren().add(myList);
    myList.setOnMouseClicked(e -> inputSetter.setInput(myList.getSelectionModel().getSelectedItem()));
  }

  /**
   * Allows list to be binded to commands or
   * variables in backend
   * @param list
   */
  public void bindList(ObservableList<String> list){ myList.setItems(list); }
}

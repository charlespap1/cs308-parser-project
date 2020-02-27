package slogo.view.scrollers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Allows us to condense variables and new commands into one
 * class with same constructor
 * @author Natalie
 */

public class ListViewer extends ScrollingWindow {
  private ListView<String> myList = new ListView<>();

  public ListViewer(double elementWidthFactor, double topPadding, String title) {
    super(elementWidthFactor, topPadding, title);
    myList.setPrefSize(myWidth-2*VBOX_SPACING, myHeight);
    myTextHolder.getChildren().add(myList);
  }

  /**
   * Allows list to be binded to commands or
   * variables in backend
   * @param list
   */
  public void bindList(ObservableList<String> list){
    myList.setItems(list);
  }
}

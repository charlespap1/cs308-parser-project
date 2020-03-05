package slogo.view.scrollers;

import javafx.collections.ObservableList;

import javafx.scene.control.ListView;
import slogo.model.code.Token;

/**
 * Allows us to condense variables and new commands into one
 * class with same constructor
 * @author Natalie
 */

public abstract class ListViewer extends ScrollingWindow {
  protected ListView<Token> myList = new ListView<>();

  public ListViewer(double elementWidthFactor, double topPadding) {
    super(elementWidthFactor, topPadding);
    myList.setPrefSize(myWidth-2*VBOX_SPACING, myHeight);
    myTextHolder.getChildren().addAll(myList);
  }

  /**
   * Allows list to be binded to commands or
   * variables in backend
   * @param list
   */
  public void bindList(ObservableList<Token> list) {
    myList.setItems(list);
  }

  protected abstract void onSelectedItem(Token t);
}

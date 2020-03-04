package slogo.view.scrollers;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import slogo.model.code.Token;
import slogo.model.code.Variable;

/**
 * Allows us to condense variables and new commands into one
 * class with same constructor
 * @author Natalie
 */

public class ListViewer extends ScrollingWindow {
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
}

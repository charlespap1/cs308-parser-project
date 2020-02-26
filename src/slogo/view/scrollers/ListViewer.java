package slogo.view.scrollers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListViewer extends ScrollingWindow {
  private ListView<String> myList = new ListView<>();

  public ListViewer(double elementWidthFactor, double topPadding, String title) {
    super(elementWidthFactor, topPadding, title);
    myList.setPrefSize(myWidth-2*VBOX_SPACING, myHeight);
    myTextHolder.getChildren().add(myList);
  }

  public void bindList(ObservableList<String> list){
    myList.setItems(list);
  }
}

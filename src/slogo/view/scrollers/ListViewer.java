package slogo.view.scrollers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class ListViewer extends ScrollingWindow {
  private ListView<String> myList = new ListView<>();

  public ListViewer(double elementWidthFactor, double topPadding, String title) {
    super(elementWidthFactor, topPadding, title);
    myTextHolder.getChildren().add(myList);
  }

  public void bindList(ObservableList<String> list){
    myList.setItems(list);
  }
}

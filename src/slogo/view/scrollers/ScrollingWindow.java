package slogo.view.scrollers;

import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.model.tokens.Token;
import slogo.view.DrawingCanvas;
import slogo.view.setup.SetupScreen;
import slogo.view.StaticViewElement;

/**
 * Bare bones class which allows for scrolling and clicking on items in
 * a list bound to the backend
 * @author Juliet, Natalie
 */
public abstract class ScrollingWindow implements StaticViewElement {

  public static final double SCROLLING_SIDE_PADDING = DrawingCanvas.CANVAS_SIDE_PADDING;
  public static final double SCROLLING_TOP_PADDING = DrawingCanvas.CANVAS_TOP_PADDING;
  public static final double SCROLLING_MIDDLE_PADDING = 10;
  public static final double VBOX_SPACING = 10;
  public static final double TEXT_HOLDER_SPACING =5;
  public static final int FIRST_ELEMENT_IN_LIST = 0;

  protected VBox myHolder = new VBox(VBOX_SPACING);

  protected double myWidth = SetupScreen.WIDTH/3.0 - 2*SCROLLING_SIDE_PADDING;
  protected double myHeight = SetupScreen.HEIGHT/2.0 - SCROLLING_MIDDLE_PADDING - SCROLLING_TOP_PADDING;
  protected VBox myTextHolder = new VBox(TEXT_HOLDER_SPACING);
  protected ListView<Token> myList = new ListView<>();
  private Text myTitle = new Text();
  private ScrollPane myView = new ScrollPane();


  public ScrollingWindow(double elementWidthFactor, double topPadding) {
    myHolder.setLayoutX(elementWidthFactor*SetupScreen.WIDTH/3 + SCROLLING_SIDE_PADDING);
    myHolder.setLayoutY(topPadding);
    myHolder.setFillWidth(true);

    myHolder.setMinHeight(myHeight);
    myHolder.setMaxHeight(myHeight);
    myView.setContent(myTextHolder);
    myView.setMaxWidth(myWidth);
    myView.setMinWidth(myWidth);

    myHolder.getChildren().add(myView);

    myList.setPrefSize(myWidth-2*VBOX_SPACING, myHeight);
    myTextHolder.getChildren().addAll(myList);
    myList.setOnMouseClicked(e -> onSelectedItem(myList.getSelectionModel().getSelectedItem()));
  }

  /**
   * Allows common commands to be displayed
   * in the setup game
   * @return
   */
  public Node getView()
  {
    return myHolder;
  }

  /**
   * Sets the Title of the window according to language
   * @param sp
   */
  @Override
  public void setTitleProperty(List<StringProperty > sp) {
    myTitle.textProperty().bind(sp.get(FIRST_ELEMENT_IN_LIST));
    myHolder.getChildren().add(FIRST_ELEMENT_IN_LIST, myTitle);
  }

  /**
   * Allows list to be binded to commands or
   * variables in backend
   * @param list
   */
  public void bindList(ObservableList<Token> list) {
    myList.setItems(list);
  }

  /**
   * For lists which click to do a action
   * @param t
   */
  protected abstract void onSelectedItem(Token t);


}

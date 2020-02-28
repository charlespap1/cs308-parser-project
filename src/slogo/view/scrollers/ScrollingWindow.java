package slogo.view.scrollers;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.DrawingCanvas;
import slogo.view.SetupScreen;

/**
 * Class allows us to create a scrolling box for any element that needs scrolling
 * This used to be implemented by new commands and new variables until we changed
 * them to list viewers. As such, only one class, HistoryCanvas, extends this class
 * @author Juliet
 */
abstract class ScrollingWindow {

  public static final double SCROLLING_SIDE_PADDING = DrawingCanvas.CANVAS_SIDE_PADDING;
  public static final double SCROLLING_TOP_PADDING = DrawingCanvas.CANVAS_TOP_PADDING;
  public static final double SCROLLING_MIDDLE_PADDING = 10;
  public static final double VBOX_SPACING = 10;
  public static final double TEXT_HOLDER_SPACING =5;

  protected VBox myHolder = new VBox(VBOX_SPACING);
  protected double myWidth = SetupScreen.WIDTH/3.0 - 2*SCROLLING_SIDE_PADDING;
  protected double myHeight = SetupScreen.HEIGHT/2.0 - SCROLLING_MIDDLE_PADDING - SCROLLING_TOP_PADDING;
  protected VBox myTextHolder = new VBox(TEXT_HOLDER_SPACING);

  private StringProperty textProperty;


  public ScrollingWindow(double elementWidthFactor, double topPadding) {
    myHolder.setLayoutX(elementWidthFactor*SetupScreen.WIDTH/3 + SCROLLING_SIDE_PADDING);
    myHolder.setLayoutY(topPadding);
    myHolder.setFillWidth(true);

    ScrollPane myView = new ScrollPane();

    textProperty = new SimpleStringProperty();
    textProperty.addListener((o, oldVal, newVal) -> setTitle(newVal));

    myHolder.setMinHeight(myHeight);
    myHolder.setMaxHeight(myHeight);
    myView.setContent(myTextHolder);
    myView.setMaxWidth(myWidth);
    myView.setMinWidth(myWidth);

    myHolder.getChildren().add(myView);
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

  public void setTitle(String title)
  {
    Text myTitle = new Text(title);
    if(myHolder.getChildren().size() > 1)
    {
      myHolder.getChildren().remove(0);
    }
    myHolder.getChildren().add(0, myTitle);
  }

  public StringProperty textProperty()
  {
    return textProperty;
  }




}

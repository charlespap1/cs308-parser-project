package slogo.view.scrollers;


import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.DrawingCanvas;
import slogo.view.SetupScreen;

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


  public ScrollingWindow(double elementWidthFactor, double topPadding, String title) {
    myHolder.setLayoutX(elementWidthFactor*SetupScreen.WIDTH/3 + SCROLLING_SIDE_PADDING);
    myHolder.setLayoutY(topPadding);
    myHolder.setFillWidth(true);

    Text myTitle = new Text(title);
    ScrollPane myView = new ScrollPane();

    myHolder.setMinHeight(myHeight);
    myHolder.setMaxHeight(myHeight);
    myView.setContent(myTextHolder);
    myView.setMaxWidth(myWidth);
    myView.setMinWidth(myWidth);

    myHolder.getChildren().addAll(myTitle, myView);
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


}

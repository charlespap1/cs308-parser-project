package slogo.view.scrollers;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.Main;
import slogo.view.DrawingCanvas;

abstract class ScrollingWindow extends Object{

  public static final double SCROLLING_SIDE_PADDING = DrawingCanvas.CANVAS_SIDE_PADDING;
  public static final double SCROLLING_TOP_PADDING = DrawingCanvas.CANVAS_TOP_PADDING;
  public static final double SCROLLING_MIDDLE_PADDING = 10;
  public static final double VBOX_SPACING = 10;
  public static final double TEXT_HOLDER_SPACING =5;

  protected VBox myHolder;
  protected double myWidth;
  protected double myHeight;
  private Text myTitle;
  protected VBox myTextHolder;
  private ScrollPane myView;


  public ScrollingWindow(double elementWidthFactor, double topPadding, String title)
  {
    myHolder = new VBox(VBOX_SPACING);
    myWidth = Main.WIDTH/3 - 2*SCROLLING_SIDE_PADDING;
    myHeight = Main.HEIGHT/2 - SCROLLING_MIDDLE_PADDING - SCROLLING_TOP_PADDING;
    myHolder.setLayoutX(elementWidthFactor*Main.WIDTH/3 + SCROLLING_SIDE_PADDING);
    myHolder.setLayoutY(topPadding);

    myTextHolder = new VBox(TEXT_HOLDER_SPACING);
    myTitle = new Text(title);

    myView = new ScrollPane();
    myView.setContent(myTextHolder);

    myHolder.setMinHeight(myHeight);
    myHolder.setMaxHeight(myHeight);
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

package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HistoryCanvas {

  public static final double HISTORY_SIDE_PADDING = DrawingCanvas.CANVAS_SIDE_PADDING;
  public static final double HISTORY_TOP_PADDING = DrawingCanvas.CANVAS_TOP_PADDING;
  public static final double HISTORY_MIDDLE_PADDING = 10;
  public static final String DEFAULT_HISTORY = "Your history: ";

  private double historyCanvasWidth;
  private double historyCanvasHeight;
  private ScrollPane myView;
  private VBox myHistory;
  private Text myTitle;
  private VBox myHolder;

  public HistoryCanvas(double screenWidth, double screenHeight)
  {
    myHolder = new VBox(10);
    historyCanvasWidth = screenWidth/3 - 2*HISTORY_SIDE_PADDING;
    historyCanvasHeight = screenHeight/2 - HISTORY_MIDDLE_PADDING - HISTORY_TOP_PADDING;
    myHolder.setLayoutX(2*screenWidth/3 + DrawingCanvas.CANVAS_SIDE_PADDING);
    myHolder.setLayoutY(DrawingCanvas.CANVAS_TOP_PADDING);


    myHistory = new VBox(5);
    myTitle = new Text(DEFAULT_HISTORY);

    myHolder.getChildren().add(myTitle);

    myView = new ScrollPane();
    myView.setContent(myHistory);

    myHolder.setMinHeight(historyCanvasHeight);
    myHolder.setMaxHeight(historyCanvasHeight);
    myView.setMaxWidth(historyCanvasWidth);
    myView.setMinWidth(historyCanvasWidth);

    myHolder.getChildren().add(myView);
  }

  public void addHistory(String command)
  {
    Text newCommand = new Text(command);
    myHistory.getChildren().add(newCommand);
  }

  public Node getView()
  {
    return myHolder;
  }

}

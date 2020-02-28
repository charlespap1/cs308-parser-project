package slogo.view.scrollers;

import javafx.scene.text.Text;

/**
 * This class allows us to show the history of commands
 * which the user has executed
 * @author Juliet
 */

public class HistoryCanvas extends ScrollingWindow {


  public HistoryCanvas(double elementWidthFactor, double topPadding) {
    super(elementWidthFactor, topPadding);
  }

  /**
   * Ability to add newly executed commands to the history
   * @param command
   */
  public void addHistory(String command) { if (!command.equals("")) myTextHolder.getChildren().add(new Text(command)); }

  public void clearHistory() { myTextHolder.getChildren().removeAll(myTextHolder.getChildren()); }
}

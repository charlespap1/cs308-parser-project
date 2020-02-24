package slogo.view.scrollers;

import javafx.scene.text.Text;
import slogo.view.scrollers.ScrollingWindow;

/**
 * This class allows us to show the history of commands
 * which the user has executed
 * @author Juliet
 */

public class HistoryCanvas extends ScrollingWindow {

  public static final String DEFAULT_HISTORY = "Your history: ";

  public HistoryCanvas(double elementWidthFactor, double topPadding) {
    super(elementWidthFactor, topPadding, DEFAULT_HISTORY);
  }

  /**
   * Ability to add newly executed commands to the history
   * @param command
   */
  public void addHistory(String command) {
    if (!command.equals("")){
      Text newCommand = new Text(command);
      myTextHolder.getChildren().add(newCommand);
    }
  }

}

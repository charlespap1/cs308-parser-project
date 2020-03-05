package slogo.view.scrollers;

import slogo.model.code.Token;

/**
 * This class allows us to show the history of commands
 * which the user has executed
 * @author Juliet
 */

public class HistoryViewer extends ScrollingWindow {

  public HistoryViewer(double elementWidthFactor, double topPadding) {
    super(elementWidthFactor, topPadding);
  }

  @Override
  protected void onSelectedItem(Token t) {
    t.execute();
  }
}

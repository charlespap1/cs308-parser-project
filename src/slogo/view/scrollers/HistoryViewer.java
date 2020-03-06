package slogo.view.scrollers;

import slogo.controller.DirectExecutor;
import slogo.model.tokens.Instruction;
import slogo.model.tokens.Token;
import slogo.view.LineManager;

/**
 * This class allows us to show the history of commands
 * which the user has executed, can click to execute
 * @author Juliet, Natalie
 */

public class HistoryViewer extends ScrollingWindow {
  private DirectExecutor myExecutor;
  private LineManager myLineManager;

  public HistoryViewer(double elementWidthFactor, double topPadding) {
    super(elementWidthFactor, topPadding);
  }

  /**
   * Setup to allow for clicking to execute
   * @param executor
   * @param lineManager
   */
  public void setDirectExecutor(DirectExecutor executor, LineManager lineManager){
    myExecutor = executor;
    myLineManager = lineManager;
  }

  /**
   * Allows user to click a command to execute it
   * @param t
   */
  @Override
  protected void onSelectedItem(Token t) {
    if (t!=null) {
      myLineManager.newProgram();
      myExecutor.execute((Instruction) t);
    }
  }
}

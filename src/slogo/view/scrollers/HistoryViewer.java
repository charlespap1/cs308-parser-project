package slogo.view.scrollers;

import slogo.controller.DirectExecutor;
import slogo.model.tokens.Instruction;
import slogo.model.tokens.Token;
import slogo.view.LineManager;

/**
 * This class allows us to show the history of commands
 * which the user has executed
 * @author Juliet, natalie
 */

public class HistoryViewer extends ScrollingWindow {
  private DirectExecutor myExecutor;
  private LineManager myLineManager;

  public HistoryViewer(double elementWidthFactor, double topPadding) {
    super(elementWidthFactor, topPadding);
  }

  public void setDirectExecutor(DirectExecutor executor, LineManager lineManager){
    myExecutor = executor;
    myLineManager = lineManager;
  }

  @Override
  protected void onSelectedItem(Token t) {
    if (t!=null) {
      myLineManager.newProgram();
      myExecutor.execute((Instruction) t);
    }
  }
}

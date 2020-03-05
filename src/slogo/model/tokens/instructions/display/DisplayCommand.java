package slogo.model.tokens.instructions.display;

import slogo.model.tokens.instructions.Instruction;
import slogo.view.DisplayAction;

public abstract class DisplayCommand extends Instruction {
    protected DisplayAction myAction;

    public DisplayCommand(int numArgs) {
        super(numArgs);
    }

    public void setMyAction(DisplayAction action) {
        myAction = action;
    }
}

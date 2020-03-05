package slogo.model.tokens;

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

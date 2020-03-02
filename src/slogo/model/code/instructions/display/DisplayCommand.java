package slogo.model.code.instructions.display;

import slogo.model.code.instructions.Instruction;
import slogo.view.DisplayAction;

public abstract class DisplayCommand extends Instruction {
    protected DisplayAction myAction;

    public DisplayCommand(int numArgs) {
        super(numArgs);
    }

    public void setMyAction(DisplayAction action){
        myAction = action;
    }
}

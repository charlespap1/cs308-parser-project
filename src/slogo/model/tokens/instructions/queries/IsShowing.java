package slogo.model.tokens.instructions.queries;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

public class IsShowing extends Instruction {
    private static final int numArgs = 0;
    private TurtleAction myAction = t -> t.isVisible() ? 1 : 0;;

    public IsShowing(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleQueryToMaster(myAction); }

}

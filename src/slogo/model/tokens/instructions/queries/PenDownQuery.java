package slogo.model.tokens.instructions.queries;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

public class PenDownQuery extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> t.getIsPenUp() ? 0 : 1;

    public PenDownQuery(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleQueryToMaster(myAction); }

}

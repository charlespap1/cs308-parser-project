package slogo.model.code.instructions.queries;

import slogo.model.TurtleAction;
import slogo.model.code.instructions.Instruction;

public class TurtleShowingQuery extends Instruction {
    private static final int numArgs = 0;
    private TurtleAction myAction = t -> t.isVisible() ? 1 : 0;;

    public TurtleShowingQuery(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleQueryToMaster(myAction); }

}

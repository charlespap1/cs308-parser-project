package slogo.model.tokens.instructions.queries;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

public class YCoordinate extends Instruction {
    private static final int numArgs = 0;
    private TurtleAction myAction = t -> -t.getYPos();

    public YCoordinate(String name) {
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleQueryToMaster(myAction); }
}

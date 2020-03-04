package slogo.model.code.instructions.queries;

import slogo.model.TurtleAction;
import slogo.model.code.instructions.Instruction;

public class YCor extends Instruction {
    private static final int numArgs = 0;
    private TurtleAction myAction = t -> -t.getYPos();

    public YCor(String name) {
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleQueryToMaster(myAction); }
}

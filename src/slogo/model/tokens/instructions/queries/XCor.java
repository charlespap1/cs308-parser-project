package slogo.model.tokens.instructions.queries;

import slogo.model.Turtle;
import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

public class XCor extends Instruction {
    private static final int numArgs = 0;
    private TurtleAction myAction = Turtle::getXPos;

    public XCor(String name) {
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleQueryToMaster(myAction); }
}

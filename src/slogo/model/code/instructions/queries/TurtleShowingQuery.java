package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class TurtleShowingQuery extends Instruction {

    private static final int NUM_ARGS = 0;

    public TurtleShowingQuery(String name) {
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        this.valueOfExecution = t.isVisible() ? 1 : 0;
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}

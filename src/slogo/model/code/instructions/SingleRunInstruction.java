package slogo.model.code.instructions;

import slogo.model.Turtle;

import java.util.List;

public abstract class SingleRunInstruction extends Instruction {

    public SingleRunInstruction(int numArgs) {
        super(numArgs);
    }

    @Override
    public void execute (List<Turtle> activeTurtles) {
        performAction(activeTurtles.get(-1));
    }
}

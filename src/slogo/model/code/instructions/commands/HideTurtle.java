package slogo.model.code.instructions.commands;

import slogo.model.TurtleAction;
import slogo.model.code.instructions.Instruction;


public class HideTurtle extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> {
        t.setVisible(false);
        return 0;
    };

    public HideTurtle(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() { return myAccessor.turtleCommandToMaster(myAction); }

    @Override
    public String toString(){
        return instrName;
    }
}

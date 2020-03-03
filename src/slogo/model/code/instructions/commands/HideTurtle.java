package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class HideTurtle extends TurtleCommand {

    private static final int numArgs = 0;

    public HideTurtle(String name){
        super(numArgs);
        instrName = name;
    }

    protected void performAction (Turtle t) {
        t.setVisible(false);
        valueOfExecution = 0;
    }

    @Override
    public String toString(){
        return instrName;
    }
}

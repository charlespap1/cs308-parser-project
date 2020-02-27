package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class HideTurtle extends Instruction {

    private static final int numArgs = 0;

    public HideTurtle(String name){
        super(numArgs);
        instrName = name;
    }

    public void execute (Turtle t) {
        System.out.println("exec");
        t.setVisible(false);
        valueOfExecution = 0;
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    @Override
    public String toString(){
        return instrName;
    }
}

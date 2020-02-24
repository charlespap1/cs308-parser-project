package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Forward extends Instruction {

    private static final int NUM_ARGS = 1;

    public Forward(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        Token valueToMove = this.parameters.get(0);
        if (valueToMove instanceof Instruction) {
            ((Instruction) valueToMove).execute(t);
        }
        int valueForExec = valueToMove.generateValue();
        this.valueOfExecution = valueForExec;
        double x = t.getXPos() + valueForExec * Math.cos(Math.toRadians(t.getAngle()));
        double y = t.getYPos() + valueForExec * Math.sin(Math.toRadians(t.getAngle()));
        t.setLocation(x, y);
        t.setCurrCommand("forward " +valueForExec +"\n");
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName;
    }
}

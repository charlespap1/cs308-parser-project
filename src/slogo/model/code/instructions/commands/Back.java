package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Back extends Instruction {

    private static final int numArgs = 1;

    public Back(String name){
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
        double x = t.getXPos() - valueForExec * Math.cos(t.getAngle());
        double y = t.getYPos() - valueForExec * Math.sin(t.getAngle());
        t.setLocation(x, y);
        t.setCurrCommand("forward " +valueForExec);
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public String toString(){
        return "";
    }
}

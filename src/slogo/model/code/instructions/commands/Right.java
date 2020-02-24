package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Right extends Instruction {

    private static final int numArgs = 1;

    public Right(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        Token valueToRotate = this.parameters.get(0);
        if (valueToRotate instanceof Instruction) {
            ((Instruction) valueToRotate).execute(t);
        }
        int valueForExec = valueToRotate.generateValue();
        this.valueOfExecution = valueForExec;
        double angle = t.getAngle() - valueForExec;
        t.setAngle(angle);
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
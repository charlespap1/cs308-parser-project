package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Remainder extends Instruction {

    private static final int NUM_ARGS = 2;

    @Override
    public void execute (Turtle t) {
        Token firstVal = this.parameters.get(0);
        Token secondVal = this.parameters.get(1);
        if (firstVal instanceof Instruction) {
            ((Instruction) firstVal).execute(t);
        }
        if (secondVal instanceof Instruction) {
            ((Instruction) secondVal).execute(t);
        }
        int num1 = firstVal.generateValue();
        int num2 = secondVal.generateValue();
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    // needs to return num1 % num2
    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }

    public String toString(){
        return "";
    }
}

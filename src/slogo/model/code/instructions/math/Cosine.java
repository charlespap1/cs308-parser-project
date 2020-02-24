package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Cosine extends Instruction {

    private static final int NUM_ARGS = 1;

    @Override
    public void execute (Turtle t) {
        Token firstVal = this.parameters.get(0);
        if (firstVal instanceof Instruction) {
            ((Instruction) firstVal).execute(t);
        }
        int num1 = firstVal.generateValue();
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    // needs to return Math.cos(num1)
    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }

    public String toString(){
        return "";
    }
}

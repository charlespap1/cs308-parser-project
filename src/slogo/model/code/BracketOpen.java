package slogo.model.code;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class BracketOpen extends Instruction {

    public BracketOpen(String name){
        super();
    }

    @Override
    public void execute(Turtle turtle) {

    }

    @Override
    public int numRequiredArgs() {
        return -1;
    }

    @Override
    public int generateValue() {
        return -1;
    }
}

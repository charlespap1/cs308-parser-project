package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;
import slogo.view.ClearAction;


public class ClearScreen extends Instruction {

    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    public static final int DEFAULT_ROTATION = 90;
    private static final int numArgs = 0;

    private ClearAction clearAction;

    public ClearScreen(String name, ClearAction action){
        super(numArgs);
        instrName = name;
        clearAction = action;
    }

    public void execute (Turtle t) {
        valueOfExecution = distFrom(t.getXPos(),t.getYPos(),HOME_X,HOME_Y);
        t.setLocation(HOME_X, HOME_Y);
        t.setAngle(DEFAULT_ROTATION);
        t.setCurrCommand(toString());
        t.setCurrCommand("");
        clearAction.execute();
    }

    @Override
    public String toString(){
        return instrName;
    }
}

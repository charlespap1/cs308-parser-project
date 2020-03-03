package slogo.model.code.instructions.display;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.commands.TurtleCommand;
import slogo.view.DisplayAction;

import java.util.ArrayList;
import java.util.List;

public class PenColor extends TurtleCommand implements DisplayCommand {

    private DisplayAction myAction;
    private static final int numArgs = 0;

    public PenColor(String name) {
        super(numArgs);
        instrName = name;
    }

    protected void performAction(Turtle t) {
        //valueOfExecution = t.getPenColor();
    }

    @Override
    public void setMyAction(DisplayAction action) {
        myAction = action;
    }
}

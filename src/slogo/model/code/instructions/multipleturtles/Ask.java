package slogo.model.code.instructions.multipleturtles;

import slogo.model.Model;
import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Ask extends Instruction {

    private static final int numArgs = 2;
    //private AddNewTurtleFunction addNewTurtle;

    public Ask(String name){
        super(numArgs);
        instrName = name;
        //this.addNewTurtle = addNewTurtle;
    }

    public void performAction (Turtle t) throws InvalidArgumentException, InvalidLoopConditionException {
        Token list1 = parameters.get(0);
        Token list2 = parameters.get(1);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        List<Token> turtles = ((ListSyntax) list1).getContents();
        List<Token> commands = ((ListSyntax) list2).getContents();
        for (Token turtle : turtles) {
            int turtleId = (int) checkTokenNotListAndGetVal(turtle, t);
            Turtle tt = Model.createOrGetTurtle(turtleId);
            for (Token command : commands) {
                if (!(command instanceof Instruction)) {
                    throw new InvalidLoopConditionException();
                }
                ((Instruction) command).performAction(tt);
                valueOfExecution = command.generateValue();
            }
        }
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }
}

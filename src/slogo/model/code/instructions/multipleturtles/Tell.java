package slogo.model.code.instructions.multipleturtles;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.CommandCannotDoListException;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Tell extends Instruction {

    private static final int numArgs = 1;

    public Tell(String name){
        super(numArgs);
        instrName = name;
    }

    public void execute (Turtle t) throws InvalidArgumentException {
        Token list = parameters.get(0);
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        List<Token> turtles = ((ListSyntax) list).getContents();
        for (Token turtle : turtles) {
            double turtleId = checkTokenNotListAndGetVal(turtle, t);
            // need to create new turtle with the given turtle id
            valueOfExecution = turtleId;
        }
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }
}
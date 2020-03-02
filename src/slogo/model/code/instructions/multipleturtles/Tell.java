package slogo.model.code.instructions.multipleturtles;

import slogo.model.Model;
import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Tell extends Instruction {

    private static final int numArgs = 1;

    public Tell(String name){
        super(numArgs);
        instrName = name;
        Model.getActiveTurtles().clear();
    }

    public void performAction (Turtle t) throws InvalidArgumentException {
        Token list = parameters.get(0);
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        List<Token> turtles = ((ListSyntax) list).getContents();
        for (Token turtle : turtles) {
            int turtleId = (int) checkTokenNotListAndGetVal(turtle, t);
            Turtle tt = Model.createOrGetTurtle(turtleId);
            Model.getActiveTurtles().add(tt);
            valueOfExecution = turtleId;
        }
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }
}

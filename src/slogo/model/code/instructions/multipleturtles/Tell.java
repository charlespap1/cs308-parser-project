package slogo.model.code.instructions.multipleturtles;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidArgumentException;

import java.util.List;

public class Tell extends MultiTurtleCommand {

    private static final int numArgs = 1;

    public Tell(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public void execute() {
        Token list = parameters.get(0);
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        List<Token> turtles = ((ListSyntax) list).getContents();
        activeTurtles.clear();
        for (Token turtle : turtles) {
            int turtleId = (int) checkTokenNotListAndGetVal(turtle);
            if (!turtleMap.containsKey(turtleId)) addNewTurtleFunction.addTurtle(turtleId);
            activeTurtles.add(turtleMap.get(turtleId));
            valueOfExecution = turtleId;
        }
    }
}

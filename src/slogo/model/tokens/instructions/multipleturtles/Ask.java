package slogo.model.tokens.instructions.multipleturtles;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.ListSyntax;
import slogo.model.tokens.Token;
import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;
import slogo.model.tokens.instructions.Instruction;

import java.util.List;

public class Ask extends Instruction {

    private static final int numArgs = 2;
    private List<Double> activeTurtles;
    private List<Token> commands;

    private TurtleAction myAction = t -> {
        double returnValue = 0;
        if (activeTurtles.contains(t.getId())){
            for (Token command : commands) {
                if (!(command instanceof Instruction)) { throw new InvalidLoopConditionException(); }
                returnValue = command.execute();
            }
        }
        return returnValue;
    };

    public Ask(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        Token list1 = parameters.get(0);
        if (!(list1 instanceof ListSyntax)) throw new InvalidArgumentException();
        List<Token> turtles = ((ListSyntax) list1).getContents();
        activeTurtles = getParamsAsVals(turtles);

        Token list2 = parameters.get(1);
        if (!(list2 instanceof ListSyntax)) { throw new InvalidArgumentException(); }
        commands = ((ListSyntax) list2).getContents();

        return myAccessor.multiTurtleCommandToMaster(myAction, activeTurtles);
    }
}

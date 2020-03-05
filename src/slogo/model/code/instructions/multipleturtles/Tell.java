package slogo.model.code.instructions.multipleturtles;

import slogo.model.code.instructions.TurtleAction;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Tell extends Instruction {

    private static final int numArgs = 1;
    private List<Double> activeTurtles = new ArrayList<>();
    private TurtleAction myAction = t -> {
        if (activeTurtles.contains(t.getId())) t.active(true);
        else t.active(false);
        return t.getId();
    };

    public Tell(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        Token list = parameters.get(0);
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        List<Token> turtles = ((ListSyntax) list).getContents();
        activeTurtles = getParamsAsVals(turtles);
        return myAccessor.multiTurtleCommandToMaster(myAction, activeTurtles);
    }
}

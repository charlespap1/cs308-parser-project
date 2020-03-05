package slogo.model.code.instructions.multipleturtles;

import slogo.model.code.instructions.TurtleAction;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;


import java.util.ArrayList;
import java.util.List;

public class AskWith extends Instruction {

    private static final int numArgs = 2;
    private Instruction myCondition;
    private List<Token> commands;

    private TurtleAction myAction = t -> {
        double returnValue = Integer.MIN_VALUE;
        double val = myCondition.execute();
        System.out.println(" condition: " + val);
        if ( val == 1){
            for (Token command : commands) {
                if (!(command instanceof Instruction)) { throw new InvalidLoopConditionException(); }
                returnValue = command.execute();
            }
        }

        return returnValue;
    };

    public AskWith(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        System.out.println("executing");
        Token list1 = parameters.get(0);
        Token list2 = parameters.get(1);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) throw new InvalidArgumentException();

        List<Token> condition = ((ListSyntax) list1).getContents();
        Token conditionToken = condition.get(0);
        System.out.println(conditionToken);
        if (!(conditionToken instanceof Instruction)) { throw new InvalidLoopConditionException(); }
        myCondition = (Instruction) conditionToken;
        commands = ((ListSyntax) list2).getContents();

        return myAccessor.multiTurtleCommandToMaster(myAction, new ArrayList<>());
    }
}

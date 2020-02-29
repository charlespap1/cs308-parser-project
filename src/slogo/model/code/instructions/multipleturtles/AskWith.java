package slogo.model.code.instructions.multipleturtles;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class AskWith extends Instruction {

    private static final int numArgs = 2;

    public AskWith(String name){
        super(numArgs);
        instrName = name;
    }

    public void execute (Turtle t) throws InvalidArgumentException, InvalidLoopConditionException {
        Token list1 = parameters.get(0);
        Token list2 = parameters.get(1);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        List<Token> condition = ((ListSyntax) list1).getContents();
        Token conditionToken = condition.get(0);
        if (!(conditionToken instanceof Instruction)) {
            throw new InvalidLoopConditionException();
        }
        List<Token> commands = ((ListSyntax) list2).getContents();
//        for (Turtle tt : turtleMap.getValues()) {
//            conditionToken.execute(tt);
//            // condition is true
//            if (conditionToken.generateValue() == 1) {
//                for (Token command : commands) {
//                    if (!(command instanceof Instruction)) {
//                        throw new InvalidLoopConditionException();
//                    }
//                    ((Instruction) command).execute(tt);
//                    valueOfExecution = command.generateValue();
//                }
//            }
//        }
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }
}
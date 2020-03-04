//package slogo.model.code.instructions.multipleturtles;
//
//import slogo.model.Model;
//import slogo.model.Turtle;
//import slogo.model.code.ListSyntax;
//import slogo.model.code.Token;
//import slogo.model.code.exceptions.InvalidArgumentException;
//import slogo.model.code.exceptions.InvalidLoopConditionException;
//import slogo.model.code.instructions.Instruction;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AskWith extends MultiTurtleCommand {
//
//    private static final int numArgs = 2;
//
//    public AskWith(String name){
//        super(numArgs);
//        instrName = name;
//    }
//
//    @Override
//    public void execute() {
//        Token list1 = parameters.get(0);
//        Token list2 = parameters.get(1);
//        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) {
//            throw new InvalidArgumentException();
//        }
//        List<Token> condition = ((ListSyntax) list1).getContents();
//        Token conditionToken = condition.get(0);
//        if (!(conditionToken instanceof Instruction)) {
//            throw new InvalidLoopConditionException();
//        }
//        List<Token> commands = ((ListSyntax) list2).getContents();
//        List<Turtle> prevActiveTurtles = new ArrayList<>(activeTurtles);
//        activeTurtles.clear();
//        for (Turtle t : turtleMap.values()) {
//            ((Instruction) conditionToken).execute();
//            // condition is true
//            if (conditionToken.generateValue() == 1) {
//                activeTurtles.add(t);
//            }
//        }
//        for (Token command : commands) {
//            if (!(command instanceof Instruction)) {
//                throw new InvalidLoopConditionException();
//            }
//            ((Instruction) command).execute();
//            valueOfExecution = command.generateValue();
//        }
//        activeTurtles = prevActiveTurtles;
//    }
//}

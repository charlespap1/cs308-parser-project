//package slogo.model.code.instructions.misc;
//
//import slogo.model.Turtle;
//import slogo.model.code.ListSyntax;
//import slogo.model.code.Token;
//import slogo.model.code.exceptions.InvalidArgumentException;
//import slogo.model.code.exceptions.InvalidLoopConditionException;
//import slogo.model.code.instructions.Instruction;
//
//import java.util.List;
//
//public class Repeat extends Instruction {
//
//    private static final int numArgs = 2;
//
//    public Repeat(String name){
//        super(numArgs);
//        this.instrName = name;
//    }
//
//    public void execute () {
//        Token expr = this.parameters.get(0);
//        double numRepeats = checkTokenNotListAndGetVal(expr);
//        Token list = this.parameters.get(1);
//        if (!(list instanceof ListSyntax)) {
//            throw new InvalidArgumentException();
//        }
//
//        List<Token> commands = ((ListSyntax) list).getContents();
//        for (int i = 0; i < numRepeats; i++) {
//            for (Token command : commands) {
//                if (!(command instanceof Instruction)) {
//                    throw new InvalidLoopConditionException();
//                }
//                ((Instruction) command).execute();
//                this.valueOfExecution = command.generateValue();
//            }
//        }
//    }
//
//    @Override
//    public String toString(){ return instrName + ": "; }
//}

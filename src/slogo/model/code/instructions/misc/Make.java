//package slogo.model.code.instructions.misc;
//
//import slogo.model.Turtle;
//import slogo.model.code.Token;
//import slogo.model.code.Variable;
//import slogo.model.code.exceptions.InvalidArgumentException;
//import slogo.model.code.exceptions.InvalidLoopConditionException;
//import slogo.model.code.instructions.Instruction;
//
//public class Make extends Instruction {
//
//    private static final int numArgs = 2;
//
//    public Make(String name){
//        super(numArgs);
//        this.instrName = name;
//    }
//
//    public void execute () {
//        Token var = this.parameters.get(0);
//        Token expr = this.parameters.get(1);
//        if (!(var instanceof Variable)) {
//            throw new InvalidArgumentException();
//        }
//        double val = checkTokenNotListAndGetVal(expr);
//        ((Variable) var).setVariable(val);
//        this.valueOfExecution = val;
//    }
//
//    public String toString(String var){ return instrName +" " + var; }
//}

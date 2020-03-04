//package slogo.model.code.instructions.booleans;
//
//import slogo.model.code.instructions.Instruction;
//
//import java.util.List;
//
//public class Not extends Instruction {
//
//    private static final int numArgs = 1;
//
//    public Not(String name) {
//        super(numArgs);
//        instrName = name;
//    }
//
//    public void execute () {
//        List<Double> paramsAsVals = this.getParamsAsVals();
//        double val = paramsAsVals.get(0);
//        valueOfExecution = (val == 0) ? 1 : 0;
//    }
//
//    public String toString(double val){
//        return instrName + val + " = " + valueOfExecution;
//    }
//}

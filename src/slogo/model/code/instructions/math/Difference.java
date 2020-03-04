//package slogo.model.code.instructions.math;
//
//import slogo.model.code.instructions.Instruction;
//
//import java.util.List;
//
//public class Difference extends Instruction {
//
//    private static final int numArgs = 2;
//
//    public Difference(String name) {
//        super(numArgs);
//        instrName = name;
//    }
//
//    public void execute () {
//        List<Double> paramsAsVals = this.getParamsAsVals();
//        double val1 = paramsAsVals.get(0);
//        double val2 = paramsAsVals.get(1);
//        valueOfExecution = val1 - val2;
//    }
//
//    public String toString(double val1, double val2){
//        return val1 + " - " + val2 + " = " + valueOfExecution;
//    }
//}

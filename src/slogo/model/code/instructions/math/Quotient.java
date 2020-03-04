//package slogo.model.code.instructions.math;
//
//import slogo.model.code.exceptions.DivideByZeroException;
//import slogo.model.code.instructions.Instruction;
//
//import java.util.List;
//
//public class Quotient extends Instruction {
//
//    private static final int numArgs = 2;
//
//    public Quotient(String name) {
//        super(numArgs);
//        instrName = name;
//    }
//
//    public void execute () throws DivideByZeroException {
//        List<Double> paramsAsVals = this.getParamsAsVals();
//        double val1 = paramsAsVals.get(0);
//        double val2 = paramsAsVals.get(1);
//        if (val2 == 0 && val1 != 0) throw new DivideByZeroException();
//        valueOfExecution = val1 / val2;
//    }
//
//    public String toString(double val1, double val2){
//        return val1 + " / " + val2 + " = " + valueOfExecution;
//    }
//}

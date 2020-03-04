//package slogo.model.code.instructions.commands;
//
//import slogo.model.Turtle;
//import slogo.model.code.instructions.Instruction;
//
//import java.util.List;
//
//public class SetXY extends TurtleCommand {
//
//    private static final int numArgs = 2;
//
//    public SetXY(String name){
//        super(numArgs);
//        instrName = name;
//    }
//
//    protected void performAction (Turtle t) {
//        List<Double> paramsAsInts = getParamsAsVals();
//        double xCord = paramsAsInts.get(0);
//        double yCord = paramsAsInts.get(1);
//        valueOfExecution = distFrom(xCord,yCord,t.getXPos(),t.getYPos());
//        t.setLocation(xCord, -yCord);
//    }
//
//    private double distFrom(double x, double y, double x2, double y2){
//        return Math.sqrt(Math.pow(x2 - x,2) + Math.pow(y2 - y,2));
//    }
//
//    public String toString(double x, double y){
//        return instrName + " " + x + " " + y;
//    }
//}

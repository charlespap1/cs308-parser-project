//package slogo.model.code.instructions.display;
//
//import slogo.model.code.instructions.Instruction;
//import slogo.view.DisplayAction;
//
//import java.sql.SQLOutput;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SetBackground extends Instruction implements DisplayCommand {
//
//    private DisplayAction myAction;
//    private static final int numArgs = 1;
//
//    public SetBackground(String name) {
//        super(numArgs);
//        instrName = name;
//    }
//
//    @Override
//    public void execute() {
//        List<Double> paramsAsVals = getParamsAsVals();
//        double index = paramsAsVals.get(0);
//        try {
//            myAction.execute(new ArrayList<>(List.of((int) index)));
//        } catch (Exception e) {
//            System.out.println("Bad input");
//        }
//        valueOfExecution = index;
//    }
//
//    @Override
//    public void setMyAction(DisplayAction action) {
//        myAction = action;
//    }
//}

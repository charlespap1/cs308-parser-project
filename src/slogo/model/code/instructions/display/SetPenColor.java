package slogo.model.code.instructions.display;

import slogo.model.Turtle;

import java.util.List;

public class SetPenColor extends DisplayCommand {
    public static int numArgs = 1;

    public SetPenColor(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public void performAction(Turtle turtle) {
        List<Double> paramsAsVals = getParamsAsVals(turtle);
        valueOfExecution = paramsAsVals.get(0);
        try {
            myAction.execute(paramsAsVals);
        } catch (Exception e) {
            // TODO: error handling
            System.out.println("bad method");
        }
    }
}

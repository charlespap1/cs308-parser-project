package slogo.model.tokens;

import slogo.view.DisplayAction;

import java.util.List;

public abstract class DisplayCommand extends Instruction {
    protected DisplayAction myAction;

    public DisplayCommand(int numArgs) {
        super(numArgs);
    }

    public void setMyAction(DisplayAction action) {
        myAction = action;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = getParamsAsVals();
        try {
            myAction.execute(paramsAsVals);
        } catch (Exception e) {
            System.out.println("Bad input");
        }
        return paramsAsVals.get(0);
    }
}

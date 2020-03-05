package slogo.model.code.instructions.display;

import slogo.model.code.instructions.Instruction;
import slogo.view.DisplayAction;

import java.util.ArrayList;
import java.util.List;

public class SetShape extends DisplayCommand {
    private static final int numArgs = 1;

    public SetShape(String name) {
        super(numArgs);
        instrName = name;
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

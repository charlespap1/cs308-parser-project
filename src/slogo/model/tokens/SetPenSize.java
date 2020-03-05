package slogo.model.tokens;

import java.util.List;

public class SetPenSize extends DisplayCommand {
    private static final int numArgs = 1;

    public SetPenSize(String name) {
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

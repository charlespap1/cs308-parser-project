package slogo.model.code.instructions.display;

import java.util.ArrayList;
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
        double pixels = paramsAsVals.get(0);
        try {
            myAction.execute(new ArrayList<>(List.of((int) pixels)));
        } catch (Exception e) {
            System.out.println("Bad input");
        }
        return pixels;
    }
}

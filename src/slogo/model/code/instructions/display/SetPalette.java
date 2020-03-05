package slogo.model.code.instructions.display;

import slogo.model.code.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class SetPalette extends DisplayCommand {
    private static final int numArgs = 4;

    public SetPalette(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() throws InvalidArgumentException {
        List<Double> paramsAsVals = getParamsAsVals();
        for (int i=1; i<=3; i++) if (!(rgbValueIsValid(paramsAsVals.get(i)))) throw new InvalidArgumentException();

        try {
            myAction.execute(paramsAsVals);
        } catch (Exception e) {
            System.out.println("Bad input");
        }
        return paramsAsVals.get(0);
    }

    private boolean rgbValueIsValid (double value) {
        return value >= 0 && value < 256;
    }
}

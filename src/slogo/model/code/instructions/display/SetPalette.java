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
        double index = paramsAsVals.get(0);
        double r = paramsAsVals.get(1);
        double g = paramsAsVals.get(2);
        double b = paramsAsVals.get(3);
        if (!(rgbValueIsValid(r) && rgbValueIsValid(g) && rgbValueIsValid(b))) {
            throw new InvalidArgumentException();
        }
        try {
            myAction.execute(new ArrayList<>(List.of((int) index, (int) r, (int) g, (int) b)));
        } catch (Exception e) {
            System.out.println("Bad input");
        }
        return index;
    }

    private boolean rgbValueIsValid (double value) {
        return value >= 0 && value < 256;
    }
}

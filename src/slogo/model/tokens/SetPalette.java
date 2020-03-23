package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;

import java.util.List;

/**
 * SetPalette Instruction class.
 * @author Charles, Natalie, Michael
 */
public class SetPalette extends DisplayCommand {
    private static final int numArgs = 4;
    private static final int RGB_MAX_VALUE = 256;

    /**
     * Constructs new SetPalette.
     * @param name String name of command, used by toString
     */
    public SetPalette(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Checks validity of RGB values given, calls front end function to change palette at index in parameter 0
     * to RGB value defined by parameters 1, 2, and 3.
     * @return palette index set
     */
    @Override
    public double execute() throws InvalidArgumentException {
        List<Double> paramsAsVals = getParamsAsVals();
        for (int i = 1; i < paramsAsVals.size(); i++)
            if (!(rgbValueIsValid(paramsAsVals.get(i)))) throw new InvalidArgumentException();
        myAction.execute(paramsAsVals);
        return paramsAsVals.get(0);
    }

    private boolean rgbValueIsValid(double value) {
        return value >= 0 && value < RGB_MAX_VALUE;
    }
}

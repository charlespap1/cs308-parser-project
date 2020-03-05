package slogo.model.code.instructions.display;
import java.util.List;

public class SetPenColor extends DisplayCommand {
    private static final int numArgs = 1;

    public SetPenColor(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = getParamsAsVals();
        try {
            myAction.execute(getParamsAsVals());
        } catch (Exception e) {
            System.out.println("Bad input");
        }
        return paramsAsVals.get(0);
    }
}

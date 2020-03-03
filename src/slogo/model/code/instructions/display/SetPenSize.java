package slogo.model.code.instructions.display;

import slogo.model.code.instructions.Instruction;
import slogo.view.DisplayAction;

import java.util.ArrayList;
import java.util.List;

public class SetPenSize extends Instruction implements DisplayCommand {

    private DisplayAction myAction;
    private static final int numArgs = 1;

    public SetPenSize(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public void execute() {
        List<Double> paramsAsVals = getParamsAsVals();
        double pixels = paramsAsVals.get(0);
        try {
            myAction.execute(new ArrayList<>(List.of((int) pixels)));
        } catch (Exception e) {
            System.out.println("Bad input");
        }
        valueOfExecution = pixels;
    }

    @Override
    public void setMyAction(DisplayAction action) {
        myAction = action;
    }
}

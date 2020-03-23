package slogo.model.tokens;

import slogo.view.DisplayAction;

import java.util.List;

/**
 * Abstract class extended by commands that alter or retrieve some aspect of the appearance and need to access
 * a method in the front end to do so.
 * @author Natalie
 */
public abstract class DisplayCommand extends Instruction {
    protected DisplayAction myAction;

    /**
     * Creates a new DisplayCommand
     * @param numArgs number of arguments command needs
     */
    public DisplayCommand(int numArgs) {
        super(numArgs);
    }

    /**
     * Gives the DisplayCommand access to a function in the front end to access some element of the appearance of
     * the window/turtles.
     * @param action the function a particular DisplayCommand will call
     */
    public void setMyAction(DisplayAction action) {
        myAction = action;
    }

    /**
     * Executes the command by calling the front end function and passing the command's parameter as a
     * parameter to the front end method.
     * @return execution value of front end method
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = getParamsAsVals();
        return myAction.execute(paramsAsVals);
    }
}

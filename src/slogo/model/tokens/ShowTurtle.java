package slogo.model.tokens;

public class ShowTurtle extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> {
        t.setVisible(true);
        return 1;
    };

    public ShowTurtle(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public String toString() {
        return instrName;
    }

    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

}

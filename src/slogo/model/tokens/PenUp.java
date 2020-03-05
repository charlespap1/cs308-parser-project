package slogo.model.tokens;

public class PenUp extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> {
        t.setPenUp(true);
        return 0;
    };

    public PenUp(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public String toString(){
        return instrName;
    }

    @Override
    public double execute(){ return myAccessor.turtleCommandToMaster(myAction); }
}

package slogo.model.tokens;

public class ListStart extends Instruction {

    private static final int numArgs = -1;

    public ListStart(String name){
        super(numArgs);
    }


    public double execute() {
        return -1;
    }

    @Override
    public String toString() { return " [ "; }
}

package slogo.model.code.instructions.multipleturtles;

public class Turtles extends MultiTurtleCommand {

    private static final int numArgs = 0;

    public Turtles(String name){
        super(numArgs);
        instrName = name;
        System.out.println("hi");
    }

    @Override
    public void execute() {
        valueOfExecution = turtleMap.size();
    }
}

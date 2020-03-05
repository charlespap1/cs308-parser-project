package slogo.model.code.instructions.display;

public class Shape extends DisplayCommand {
    private static final int numArgs = 0;

    public Shape(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        double shape = -1;
        try{
            shape = myAction.execute(null);
        } catch (Exception e) {
            //TODO errors
            System.out.println("bad method");
        }
        return shape;
    }
}

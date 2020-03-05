package slogo.model.tokens.instructions.display;

public class PenColor extends DisplayCommand {
    private static final int numArgs = 0;

    public PenColor(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        double penColor = -1;
        try{
            penColor = myAction.execute(null);
        } catch (Exception e) {
            //TODO errors
            System.out.println("bad method");
        }
        return penColor;
    }
}

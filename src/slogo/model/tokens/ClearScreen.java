package slogo.model.tokens;


public class ClearScreen extends DisplayCommand {
    private static final int numArgs = 0;

    public ClearScreen(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute () {
        double distanceMoved = -1;
        try{
            distanceMoved = myAction.execute(null);
        } catch (Exception e){
            // TODO: error handling
            System.out.println("bad method");
        }
        return distanceMoved;
    }
}

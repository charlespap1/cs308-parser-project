//package slogo.model.code.instructions.commands;
//
//import slogo.model.Turtle;
//import slogo.model.code.instructions.display.DisplayCommand;
//import slogo.model.code.instructions.queries.QueryCommand;
//import slogo.view.DisplayAction;
//
//import java.awt.geom.Point2D;
//
//
//public class ClearScreen extends TurtleCommand implements DisplayCommand {
//
//    //TODO not turtle command
//    public static final int HOME_X = 0;
//    public static final int HOME_Y = 0;
//    public static final int DEFAULT_ROTATION = 90;
//    private static final int numArgs = 0;
//    private DisplayAction myAction;
//
//    public ClearScreen(String name){
//        super(numArgs);
//        instrName = name;
//    }
//
//    @Override
//    public void execute () {
//        super.execute();
//        try{
//            myAction.execute(null);
//        } catch (Exception e){
//            // TODO: error handling
//            System.out.println("bad method");
//        }
//    }
//
//    protected void performAction(Turtle turtle){
//        valueOfExecution = Point2D.distance(turtle.getXPos(), turtle.getYPos(), HOME_X, HOME_Y);
//        turtle.setLocation(HOME_X, HOME_Y);
//        turtle.setAngle(DEFAULT_ROTATION);
//    }
//
//    @Override
//    public String toString(){
//        return instrName;
//    }
//
//    @Override
//    public void setMyAction(DisplayAction action) {
//        myAction = action;
//    }
//}

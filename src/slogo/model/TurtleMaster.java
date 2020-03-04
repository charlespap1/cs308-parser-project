package slogo.model;

import slogo.controller.AddNewTurtleFunction;
import slogo.model.code.instructions.Instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleMaster {

    private Map<Integer, Turtle> turtleMap = new HashMap<>();
    private List<Turtle> activeTurtles = new ArrayList<>();
    private Turtle currentActiveTurtle;
    private boolean inLoop = false;

    public Turtle addTurtle(int id) {
        Turtle newTurtle = new Turtle(id, 0, 0, false, 0);
        turtleMap.put(id, newTurtle);
        activeTurtles.add(newTurtle);
        return newTurtle;
    }

    public void setAddTurtleFunction(AddNewTurtleFunction function) { }//addTurtleFunction = function; }

    public double executeTurtleCommand(TurtleAction action){
        double executionValue=-1;
        if (inLoop){
            //TODO: think about whether this will always have the right turtle
            System.out.println("inloop already");
            executionValue = action.actOnTurtle(currentActiveTurtle);
        } else {
            inLoop = true;
            System.out.println("starting loop");
            for (Turtle t: activeTurtles) {
                currentActiveTurtle = t;
                executionValue = action.actOnTurtle(t);
            }
            System.out.println("ending loop");
            inLoop = false;
        }
        return executionValue;
    }

    public double executeTurtleQuery(TurtleAction action){
        return action.actOnTurtle(currentActiveTurtle);
    }
}

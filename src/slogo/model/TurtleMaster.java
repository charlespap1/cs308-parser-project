package slogo.model;

import slogo.controller.AddNewTurtleFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleMaster {

    private Map<Integer, Turtle> turtleMap = new HashMap<>();
    private List<Turtle> activeTurtles = new ArrayList<>();
    private Turtle currentActiveTurtle;

    public Turtle addTurtle(int id) {
        Turtle newTurtle = new Turtle(id, 0, 0, false, 0);
        turtleMap.put(id, newTurtle);
        activeTurtles.add(newTurtle);
        return newTurtle;
    }

    public void setAddTurtleFunction(AddNewTurtleFunction function) { }//addTurtleFunction = function; }

    public double executeTurtleCommand(TurtleAction action){
        double executionValue=-1;
        for (Turtle t: activeTurtles){
            currentActiveTurtle = t;
            executionValue = action.actOnTurtle(t);
        }
        return executionValue;
    }

    public double executeTurtleQuery(TurtleAction action){
        return action.actOnTurtle(currentActiveTurtle);
    }
}

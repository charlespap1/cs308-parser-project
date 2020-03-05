package slogo.model;

import slogo.controller.AddNewTurtleFunction;
import slogo.model.code.instructions.TurtleAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleMaster {

    private AddNewTurtleFunction addTurtleFunction;
    private Map<Double, Turtle> turtleMap = new HashMap<>();
    private Turtle currentActiveTurtle;
    private boolean inLoop = false;

    public void setAddTurtleFunction(AddNewTurtleFunction function) {
        addTurtleFunction = function;
        double firstTurtleId = 0.0;
        addTurtle(firstTurtleId);
        currentActiveTurtle = turtleMap.get(firstTurtleId);
    }

    public double executeTurtleCommand(TurtleAction action){
        double executionValue = 0;
        if (inLoop){
            executionValue = action.actOnTurtle(currentActiveTurtle);
        } else {
            inLoop = true;
            for (Turtle t: turtleMap.values()) {
                if (t.isActive()) {
                    currentActiveTurtle = t;
                    executionValue = action.actOnTurtle(t);
                }
            }
            inLoop = false;
        }
        return executionValue;
    }

    public double executeMultiTurtleCommand(TurtleAction action, List<Double> turtles) {
        for (Double id : turtles) if (!turtleMap.containsKey(id)) addTurtle(id);
        double executionValue = -1;
        for (Turtle t: turtleMap.values()) {
            Turtle previousActiveTurtle = currentActiveTurtle;
            currentActiveTurtle = t;
            double currentExecutionValue = action.actOnTurtle(t);
            if (currentExecutionValue != Integer.MIN_VALUE || turtles.contains(t.getId())) executionValue = currentExecutionValue;
            if (!t.isActive()) currentActiveTurtle = previousActiveTurtle;
        }
        return executionValue;
    }

    public double executeTurtleQuery(TurtleAction action){
        return action.actOnTurtle(currentActiveTurtle);
    }

    private void addTurtle(double id){
        Turtle newTurtle = new Turtle(id, 0, 0, false, 0);
        turtleMap.put(id, newTurtle);
        addTurtleFunction.addTurtle(newTurtle);
    }
}

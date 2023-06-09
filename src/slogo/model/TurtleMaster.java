package slogo.model;

import slogo.controller.AddNewTurtleFunction;
import slogo.model.history.State;
import slogo.model.tokens.TurtleAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages all instances of Turtle in a given instance of Model.
 */
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

    public double executeTurtleCommand(TurtleAction action) {
        double executionValue = 0;
        if (inLoop) {
            executionValue = action.actOnTurtle(currentActiveTurtle);
        } else {
            inLoop = true;
            for (Turtle t : turtleMap.values()) {
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
        inLoop = true;
        for (Turtle t : turtleMap.values()) {
            Turtle previousActiveTurtle = currentActiveTurtle;
            currentActiveTurtle = t;
            double currentExecutionValue = action.actOnTurtle(t);
            if (currentExecutionValue != Integer.MIN_VALUE || turtles.contains(t.getId()))
                executionValue = currentExecutionValue;
            if (!t.isActive()) currentActiveTurtle = previousActiveTurtle;
        }
        inLoop = false;
        return executionValue;
    }

    public double executeTurtleQuery(TurtleAction action) {
        return action.actOnTurtle(currentActiveTurtle);
    }

    public Map<Double, State> generateStateMap() {
        Map<Double, State> stateMap = new HashMap<>();
        for (double id : turtleMap.keySet()) {
            stateMap.put(id, new State(turtleMap.get(id)));
        }
        return stateMap;
    }

    public void updateTurtlesWithStates(Map<Double, State> turtleStates) {
        for (double id : turtleMap.keySet()) {
            if (!turtleStates.containsKey(id)) {
                Turtle t = turtleMap.get(id);
                t.setDefault();
                t.setVisible(false);
                t.setActive(false);
            } else {
                updateSingleTurtle(turtleMap.get(id), turtleStates.get(id));
            }
        }
    }

    private void updateSingleTurtle(Turtle turtle, State state) {
        turtle.setLocation(state.getxPos(), state.getyPos());
        turtle.setAngle(state.getAngle());
        turtle.setVisible(state.getIsVisible());
        turtle.setActive(state.getIsActive());
    }

    private void addTurtle(double id) {
        Turtle newTurtle = new Turtle(id);
        turtleMap.put(id, newTurtle);
        addTurtleFunction.addTurtle(newTurtle);
    }
}

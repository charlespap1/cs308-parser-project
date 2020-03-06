package slogo.model;

import slogo.model.tokens.TurtleAction;

import java.util.List;

public interface TurtleMasterAccessor {
    double turtleCommandToMaster(TurtleAction action);
    double turtleQueryToMaster(TurtleAction action);
    double multiTurtleCommandToMaster(TurtleAction action, List<Double> turtles);
}

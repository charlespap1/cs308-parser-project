package slogo.model;

import slogo.model.tokens.TurtleAction;

import java.util.List;

/**
 * Interface to allow commands that act directly on turtles to pass their actions to the
 * TurtleMaster, so commands do not need access to turtles.
 */
public interface TurtleMasterAccessor {
    double turtleCommandToMaster(TurtleAction action);

    double turtleQueryToMaster(TurtleAction action);

    double multiTurtleCommandToMaster(TurtleAction action, List<Double> turtles);
}

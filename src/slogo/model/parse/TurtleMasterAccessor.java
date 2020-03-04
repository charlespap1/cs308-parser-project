package slogo.model.parse;

import slogo.model.TurtleAction;

public interface TurtleMasterAccessor {
    double turtleCommandToMaster(TurtleAction action);
    double turtleQueryToMaster(TurtleAction action);
}

package slogo.model.tokens;

import slogo.model.Turtle;

/**
 * Interface to allow display commands to call a front end action.
 */
@FunctionalInterface
public interface TurtleAction {
    double actOnTurtle(Turtle t);
}

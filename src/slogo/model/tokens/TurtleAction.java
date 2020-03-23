package slogo.model.tokens;

import slogo.model.Turtle;

/**
 * Interface to allow display commands to call a front end action on a given Turtle.
 * @author Natalie
 */
@FunctionalInterface
public interface TurtleAction {
    double actOnTurtle(Turtle t);
}

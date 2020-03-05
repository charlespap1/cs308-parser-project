package slogo.model.tokens;

import slogo.model.Turtle;

@FunctionalInterface
public interface TurtleAction {
    double actOnTurtle(Turtle t);
}

package slogo.model.code.instructions;

import slogo.model.Turtle;

@FunctionalInterface
public interface TurtleAction {
    double actOnTurtle(Turtle t);
}

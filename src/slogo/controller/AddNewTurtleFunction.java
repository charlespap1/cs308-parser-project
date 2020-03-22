package slogo.controller;


import slogo.model.Turtle;

/**
 * Interface to pass back end turtle to front end turtle for configuration.
 * @author Natalie
 */
@FunctionalInterface
public interface AddNewTurtleFunction {
    void addTurtle(Turtle turtle);
}

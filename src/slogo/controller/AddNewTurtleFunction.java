package slogo.controller;


import slogo.model.Turtle;

@FunctionalInterface
public interface AddNewTurtleFunction {
    void addTurtle(Turtle turtle);
}
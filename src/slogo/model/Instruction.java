package slogo.model;

import slogo.State;

import java.util.List;
import java.util.Map;

public interface Instruction {

    List<State> execute(Turtle turtle, Map<String, Variable> vars);
    List<String> getNeededVarNames();
}

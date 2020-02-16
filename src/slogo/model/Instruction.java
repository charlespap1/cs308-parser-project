package slogo.model;

import slogo.State;

import java.util.List;

public interface Instruction {

    List<State> execute(State currentState, List<Variable> vars);
    List<String> getNeededVarNames();
}

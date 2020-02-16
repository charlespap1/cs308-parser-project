package slogo.model;

import slogo.State;

import java.util.List;

public interface Model {

    List<State> executeCode(String rawCode);
}

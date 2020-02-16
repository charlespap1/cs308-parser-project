package slogo.view;

import slogo.State;

public interface View {

    String getInstruction() throws NullPointerException;
    void updateDisplay(State nextState);
    void showError(String errorMessage);
}

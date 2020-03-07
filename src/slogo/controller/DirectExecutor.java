package slogo.controller;

import slogo.model.tokens.Instruction;

/**
 * Interface to pass an instruction or string directly from the history list or
 * variable list to the back end to execute.
 */
public interface DirectExecutor {
    void execute(Instruction i);

    void execute(String s);
}

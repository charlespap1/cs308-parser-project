package slogo.controller;

import slogo.model.tokens.Instruction;

public interface DirectExecutor {
    void execute(Instruction i);
    void execute(String s);
}

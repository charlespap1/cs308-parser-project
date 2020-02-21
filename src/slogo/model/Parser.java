package slogo.model;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Parser {

    Stack commands;
    Stack arguments;

    public Parser() {
        commands = new Stack();
        arguments = new Stack();
    }

    public Map<String, Variable> parseVars(String rawString) {
        return null;
    }

    public List<Instruction> parseInstructions(String rawString) {
        return null;
    }
}

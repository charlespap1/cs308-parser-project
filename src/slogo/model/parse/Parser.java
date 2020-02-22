package slogo.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Parser {

    public static final String NEW_LINE = "\n";
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
        List<String> lines = Arrays.asList(rawString.split(NEW_LINE));
        while(!lines.isEmpty()){
            String currLine = lines.remove(0);
            List<String> pieces = Arrays.asList(currLine.split(" "));
        }
        return null;
    }
}

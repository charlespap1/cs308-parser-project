package slogo.model;

import java.util.List;
import java.util.Map;

public class ModelAPI {

    private Parser p;
    private Turtle turtle;

    public ModelAPI() {
        p = new Parser();
        turtle = new Turtle(0, 0, true, 0);
    }

    public void executeCode(String rawString) {
        Map<String, Variable> vars = p.parseVars(rawString);
        List<Instruction> instructions = p.parseInstructions(rawString);
        for (Instruction i : instructions) {
            i.execute(turtle, vars);
        }
    }
}

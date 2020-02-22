package slogo.model;

import slogo.model.parse.Parser;

import java.util.List;
import java.util.Map;

public class ModelAPI {

    private Parser p;
    private Turtle turtle;

    public ModelAPI() {
        p = new Parser();
        turtle = new Turtle(0, 0, true, 0);
        //TODO: just pass turtle into parser to allow execution inside?
    }

    public void executeCode(String rawString) {
        p.parseInstructions(rawString);
    }
}

package slogo.model.history;

import slogo.model.tokens.Token;
import slogo.model.tokens.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program {

    private List<Token> myInstructions = new ArrayList<>();
    private Map<Double, State> initialTurtleStates;

    public Program (Map<Double, State> initialTurtleStates) {
        this.initialTurtleStates = initialTurtleStates;
    }

    public void addNewCommand(Instruction command) {
        myInstructions.add(command);
    }

    public List<Token> getInstructionList() { return myInstructions; }

    public Map<Double, State> getInitialTurtleStates () {
        return initialTurtleStates;
    }

}

package slogo.model.history;

import slogo.model.tokens.Instruction;
import slogo.model.tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Tracks a singular program, holding initial turtle states so turtles can be set
 * to appropriate states with undo/redo and all of the instructions run
 * within this program so that instructions can be added to/removed from history.
 */
public class Program {

    private List<Token> myInstructions = new ArrayList<>();
    private Map<Double, State> initialTurtleStates;

    public Program(Map<Double, State> initialTurtleStates) {
        this.initialTurtleStates = initialTurtleStates;
    }

    public void addNewCommand(Instruction command) {
        myInstructions.add(command);
    }

    public List<Token> getInstructionList() {
        return myInstructions;
    }

    public Map<Double, State> getInitialTurtleStates() {
        return initialTurtleStates;
    }

    public void clear() {
        myInstructions.clear();
    }

}

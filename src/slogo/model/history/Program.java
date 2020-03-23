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
 *
 * @author Natalie, Michael
 */
public class Program {

    private List<Token> myInstructions = new ArrayList<>();
    private Map<Double, State> initialTurtleStates;

    /**
     * Builds a new Program with given initial turtle state map.
     * @param initialTurtleStates map of states of all turtles upon instantiation of this Program
     */
    public Program(Map<Double, State> initialTurtleStates) {
        this.initialTurtleStates = initialTurtleStates;
    }

    /**
     * Adds a new Instruction to Program's command list.
     * @param command Instruction to add
     */
    public void addNewCommand(Instruction command) {
        myInstructions.add(command);
    }

    /**
     * Getter for Program's command list.
     * @return list of commands
     */
    public List<Token> getInstructionList() {
        return myInstructions;
    }

    /**
     * Getter for Program's initial states map.
     * @return map of initial turtle states
     */
    public Map<Double, State> getInitialTurtleStates() {
        return initialTurtleStates;
    }

    /**
     * Clear instructions in this Program without affecting initial state map--necessary for undo/redo functionality.
     */
    public void clear() {
        myInstructions.clear();
    }

}

package slogo.model.history;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.tokens.Instruction;
import slogo.model.tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Tracks program history for an instance of Model. Handles undo, redo, clear history, and adds a new program with each
 * new input to the back end.
 * @author Natalie, Michael
 */
public class History {

    private List<Program> programHistory = new ArrayList<>();
    private int programPointer = -1;
    private ObservableList<Token> myHistory = FXCollections.observableArrayList();
    private BooleanProperty undoDisabled = new SimpleBooleanProperty();
    private BooleanProperty redoDisabled = new SimpleBooleanProperty();

    /**
     * Add a new Program to current History list, removes all Programs that have been undone and not redone,
     * ensures pointer is at the top of the stack.
     * @param p the Program to be added
     */
    public void addNewProgram(Program p) {
        int lastProgramIndex = programHistory.size() - 1;
        while (programPointer < lastProgramIndex) {
            programHistory.remove(lastProgramIndex);
            lastProgramIndex--;
        }
        programPointer = lastProgramIndex + 1;
        programHistory.add(p);
        setUndoRedo();
    }

    /**
     * Function to undo Program previous to pointer's position. Removes Program's instructions from history list,
     * moves pointer, passes back a map of turtle states.
     * @return map of turtle states for Program that has been undone
     * @throws IndexOutOfBoundsException if pointer out of bounds
     */
    public Map<Double, State> undo() throws IndexOutOfBoundsException {
        if (programPointer <= 0) throw new IndexOutOfBoundsException();
        Program currProgram = programHistory.get(--programPointer);
        myHistory.removeAll(currProgram.getInstructionList());
        setUndoRedo();
        return currProgram.getInitialTurtleStates();
    }

    /**
     * Function to redo Program at pointer's current position. Adds Program's instructions back to history list,
     * moves pointer, passes back a map of turtle states.
     * @return map of turtle states for the Program that has been redone
     * @throws IndexOutOfBoundsException if pointer out of bounds
     */
    public Map<Double, State> redo() throws IndexOutOfBoundsException {
        if (programPointer >= programHistory.size() - 1) throw new IndexOutOfBoundsException();
        Program currProgram = programHistory.get(programPointer);
        myHistory.addAll(currProgram.getInstructionList());
        currProgram = programHistory.get(++programPointer);
        setUndoRedo();
        return currProgram.getInitialTurtleStates();
    }

    /**
     * Adds an Instruction to the Program on the top of the stack, adds Instruction to history list.
     * @param instruction the Instruction to add
     */
    public void addCommand(Instruction instruction) {
        programHistory.get(programPointer).addNewCommand(instruction);
        myHistory.add(instruction);
    }

    /**
     * Clears Program on top of stack--necessary for undo/redo functionality.
     */
    public void clearCurrentProgram() {
        Program currProgram = programHistory.get(programPointer);
        currProgram.clear();
    }

    /**
     * Clears entire history list, resets pointer and builds a new Program.
     * @param p the new Program
     */
    public void clearAll(Program p) {
        programHistory = new ArrayList<>();
        myHistory.clear();
        programPointer = -1;
        addNewProgram(p);
    }

    /**
     * Getter to pass observable list of history to front end.
     * @return history list
     */
    public ObservableList<Token> getHistoryList() {
        return myHistory;
    }

    /**
     * Getter to pass BooleanProperty for undo button's disabled status to front end.
     * @return undo's disabled status
     */
    public BooleanProperty getUndoDisabled() {
        return undoDisabled;
    }

    /**
     * Getter to pass BooleanProperty for redo button's disabled status to front end.
     * @return redo's disabled status
     */
    public BooleanProperty getRedoDisabled() {
        return redoDisabled;
    }

    private void setUndoRedo() {
        undoDisabled.setValue(programPointer <= 0);
        redoDisabled.setValue(programPointer >= programHistory.size() - 1);
    }
}

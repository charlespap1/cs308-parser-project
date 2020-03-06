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

public class History {

    private List<Program> programHistory = new ArrayList<>();
    private int programPointer = -1;
    private ObservableList<Token> myHistory = FXCollections.observableArrayList();
    private BooleanProperty undoDisabled = new SimpleBooleanProperty();
    private BooleanProperty redoDisabled = new SimpleBooleanProperty();

    public void addNewProgram(Program p) {
        int lastProgramIndex = programHistory.size() - 1;
        while (programPointer<lastProgramIndex){
            programHistory.remove(lastProgramIndex);
            lastProgramIndex--;
        }
        programPointer = lastProgramIndex + 1;
        programHistory.add(p);
        setUndoRedo();
    }

    public Map<Double, State> undo() throws IndexOutOfBoundsException {
        if (programPointer <= 0) throw new IndexOutOfBoundsException();
        Program currProgram = programHistory.get(--programPointer);
        myHistory.removeAll(currProgram.getInstructionList());
        setUndoRedo();
        return currProgram.getInitialTurtleStates();
    }

    public Map<Double, State> redo() throws IndexOutOfBoundsException {
        if (programPointer >= programHistory.size() - 1) throw new IndexOutOfBoundsException();
        Program currProgram = programHistory.get(programPointer);
        myHistory.addAll(currProgram.getInstructionList());
        currProgram = programHistory.get(++programPointer);
        setUndoRedo();
        return currProgram.getInitialTurtleStates();
    }

    public void addCommand(Instruction instruction){
        programHistory.get(programPointer).addNewCommand(instruction);
        myHistory.add(instruction);
    }

    public void clearCurrentProgram(){
        Program currProgram = programHistory.get(programPointer);
        currProgram.clear();
    }

    public void clearAll(Program p) {
        programHistory = new ArrayList<>();
        myHistory.clear();
        programPointer = -1;
        addNewProgram(p);
    }

    public ObservableList<Token> getHistoryList() { return myHistory; }
    public BooleanProperty getUndoDisabled() { return undoDisabled; }
    public BooleanProperty getRedoDisabled() { return redoDisabled; }

    private void setUndoRedo(){
        undoDisabled.setValue(programPointer<=0);
        redoDisabled.setValue(programPointer>=programHistory.size() - 1);
    }
}

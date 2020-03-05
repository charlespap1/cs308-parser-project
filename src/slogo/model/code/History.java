package slogo.model.code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.code.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History {

    private List<Program> programHistory = new ArrayList<>();
    private int programPointer = 0;
    private ObservableList<Token> myHistory = FXCollections.observableArrayList();

    public History () {

    }

    public void addNewProgram(Program p) {
        programPointer++;
        programHistory.add(p);
    }

    public void setPointerToEnd() {
        programPointer = programHistory.size() - 1;
    }

    public Map<Double, State> undo() throws IndexOutOfBoundsException {
        if (programPointer <= 0) throw new IndexOutOfBoundsException();
        Program currProgram = programHistory.get(--programPointer);
        myHistory.removeAll(currProgram.getInstructionList());
        return currProgram.getInitialTurtleStates();
        // set turtle states to programHistory.get(programCounter)
    }

    public Map<Double, State> redo() throws IndexOutOfBoundsException {
        if (programPointer >= programHistory.size() - 1) throw new IndexOutOfBoundsException();
        Program currProgram = programHistory.get(++programPointer);
        myHistory.addAll(currProgram.getInstructionList());
        return currProgram.getInitialTurtleStates();
        // either set turtle states to programHistory.get(programCounter) or execute everything in the program (before programPointer is incremented)
    }

    public void addCommand(Instruction instruction){
        programHistory.get(programHistory.size() - 1).addNewCommand(instruction);
        myHistory.add(instruction);
    }

    public ObservableList<Token> getHistoryList() { return myHistory; }
}

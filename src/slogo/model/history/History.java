package slogo.model.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.tokens.Token;
import slogo.model.tokens.instructions.Instruction;

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
        int lastProgramIndex = programHistory.size() - 1;
        while (programPointer<lastProgramIndex){
            programHistory.remove(lastProgramIndex);
            lastProgramIndex--;
        }
        programPointer = lastProgramIndex + 1;
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
    }

    public Map<Double, State> redo() throws IndexOutOfBoundsException {
        if (programPointer >= programHistory.size() - 1) throw new IndexOutOfBoundsException();
        Program currProgram = programHistory.get(++programPointer);
        myHistory.addAll(currProgram.getInstructionList());
        return currProgram.getInitialTurtleStates();
    }

    public void addCommand(Instruction instruction){
        programHistory.get(programHistory.size() - 1).addNewCommand(instruction);
        myHistory.add(instruction);
    }

    public ObservableList<Token> getHistoryList() { return myHistory; }
}

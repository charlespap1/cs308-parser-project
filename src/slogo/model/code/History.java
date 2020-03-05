package slogo.model.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History {

    private List<Program> programHistory = new ArrayList<>();
    private int programPointer = 0;

    public History () {

    }

    public void addNewProgram(Program p) {
        programPointer++;
        programHistory.add(p);
    }

    public Program getProgram(int index) {
        return programHistory.get(index);
    }

    public void setPointerToEnd() {
        programPointer = programHistory.size() - 1;
    }

    public Map<Integer, State> undo() throws IndexOutOfBoundsException {
        if (programPointer <= 0) throw new IndexOutOfBoundsException();
        return programHistory.get(--programPointer).getInitialTurtleStates();
        // set turtle states to programHistory.get(programCounter)
    }

    public Map<Integer, State> redo() throws IndexOutOfBoundsException {
        if (programPointer >= programHistory.size()) throw new IndexOutOfBoundsException();
        return programHistory.get(++programPointer).getInitialTurtleStates();
        // either set turtle states to programHistory.get(programCounter) or execute everything in the program (before programPointer is incremented)

    }
}

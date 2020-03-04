package slogo.model.code;

import java.util.ArrayList;
import java.util.List;

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

//    public void incrementPointer() {
//        programPointer++;
//    }

    public void undo() {
        programPointer--;
        // set turtle states to programHistory.get(programPointer).
    }
}

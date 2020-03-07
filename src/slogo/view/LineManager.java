package slogo.view;

import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage all of the line action
 *
 * @author Natalie
 */
public class LineManager {

    private Group myRoot;
    private List<List<Line>> myLines = new ArrayList<>();
    private int programPointer = -1;

    public LineManager(Group root) {
        myRoot = root;
    }

    /**
     * Creates new bucket of lines to keep track of. Needed for clear, redo and undo
     */
    public void newProgram() {
        int lastListIndex = myLines.size() - 1;
        while (programPointer < lastListIndex) {
            myLines.remove(lastListIndex);
            lastListIndex--;
        }
        programPointer = lastListIndex + 1;
        myLines.add(new ArrayList<>());
    }

    /**
     * Adds a line to the bucket
     *
     * @param line
     */
    public void addLine(Line line) {
        myLines.get(myLines.size() - 1).add(line);
        myRoot.getChildren().add(line);
    }

    /**
     * Removes the most recent line from the bucket
     */
    public void undo() {
        if (programPointer >= 0) {
            List<Line> mostRecentLines = myLines.get(programPointer--);
            myRoot.getChildren().removeAll(mostRecentLines);
        }
    }

    /**
     * Puts the most recent line that was taken off back on the root
     */
    public void redo() {
        if (programPointer < myLines.size() - 1) {
            List<Line> mostRecentLines = myLines.get(++programPointer);
            myRoot.getChildren().addAll(mostRecentLines);
        }
    }

    /**
     * Clears the whole bucket of lines
     */
    public void clearAllLines() {
        for (List<Line> program : myLines) {
            myRoot.getChildren().removeAll(program);
        }
        myLines = new ArrayList<>();
        programPointer = -1;
    }
}

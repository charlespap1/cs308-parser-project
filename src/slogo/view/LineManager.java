package slogo.view;

import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class LineManager {

    private Group myRoot;
    private List<List<Line>> myLines = new ArrayList<>();
    private int programPointer = -1;
    private boolean graphicalLinesListExists = false;

    public LineManager(Group root){
        myRoot = root;
    }

    public void newProgram(){
        if (!graphicalLinesListExists){
            int lastListIndex = myLines.size() - 1;
            while (programPointer<lastListIndex){
                myLines.remove(lastListIndex);
                lastListIndex--;
            }
            programPointer = lastListIndex + 1;
            myLines.add(new ArrayList<>());
        } else {
            graphicalLinesListExists = false;
        }
    }

    public void addLine(Line line){
        myLines.get(myLines.size()-1).add(line);
        myRoot.getChildren().add(line);
    }

    public void undo() {
        if (programPointer>=0){
            List<Line> mostRecentLines = myLines.get(programPointer--);
            myRoot.getChildren().removeAll(mostRecentLines);
        }
    }

    public void redo() {
        if (programPointer<myLines.size()-1){
            List<Line> mostRecentLines = myLines.get(++programPointer);
            myRoot.getChildren().addAll(mostRecentLines);
        }
    }

    public void clearAllLines(){
        for (List<Line> program:myLines){
            myRoot.getChildren().removeAll(program);
        }
        myLines = new ArrayList<>();
        programPointer = -1;
    }

    public void checkMovingFromButtons(){
        if (myLines.size()<1) newProgram();
        graphicalLinesListExists = true;
    }
}

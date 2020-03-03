package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public abstract class TurtleCommand extends Instruction {
    List<Turtle> activeTurtles;

    public TurtleCommand(int numArgs) {
        super(numArgs);
    }

    public void setActiveTurtles(List<Turtle> turtles){
        activeTurtles = turtles;
    }

    public void execute() {
        for (Turtle t : activeTurtles) {
            performAction(t);
        }
    }

    abstract void performAction(Turtle t);
}

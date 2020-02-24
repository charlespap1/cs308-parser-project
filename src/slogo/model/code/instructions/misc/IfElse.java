package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class IfElse extends Instruction {

    private static final int NUM_ARGS = 2;

    public IfElse(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        Token expr = this.parameters.get(0);
        Token list1 = this.parameters.get(1);
        Token list2 = this.parameters.get(2);
        this.valueOfExecution = 0;
        if (expr.generateValue() != 0) {
            runCommandsInList(list1, t);
        } else {
            runCommandsInList(list2, t);
        }
    }

    public void runCommandsInList (Token list, Turtle t) {
        if (list instanceof ListSyntax) {
            List<Token> commands = ((ListSyntax) list).getContents();
            for (Token command: commands) {
                if (command instanceof Instruction) {
                    ((Instruction) command).execute(t);
                }
                this.valueOfExecution = command.generateValue();
            }
        } else {
            //throw error
        }
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName;
    }
}

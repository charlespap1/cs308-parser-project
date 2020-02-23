package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;

import java.util.Collection;
import java.util.List;

public abstract class Instruction implements Token {

    protected List<Token> parameters;

    public Instruction(){
        parameters = null;
    }

    public abstract void execute(Turtle turtle);
    public abstract int numRequiredArgs();

    public void setParameters(List<Token> params){
        parameters = params;
    }
}

package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Code;

import java.util.List;

public abstract class Instruction implements Code {

    protected List<Code> parameters;

    public Instruction(){
        parameters = null;
    }

    public abstract int execute(Turtle turtle);
    public abstract int numRequiredArgs();

    public void setParameters(List<Code> params){
        parameters = params;
    }
}

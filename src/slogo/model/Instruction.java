package slogo.model;

import slogo.State;

import java.util.List;
import java.util.Map;

public abstract class Instruction implements Code{

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

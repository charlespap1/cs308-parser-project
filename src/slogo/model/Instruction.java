package slogo.model;

import slogo.State;

import java.util.List;
import java.util.Map;

public abstract class Instruction implements Code{

    private List<Code> parameters;

    public Instruction(int val){
        parameters = null;
    }

    public abstract int execute(Turtle turtle);
    public abstract int numRequiredArgs();

    public void setParameters(List<Code> params){
        parameters = params;
    }
}

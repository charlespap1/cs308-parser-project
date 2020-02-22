package slogo.model;

import java.util.List;

public class PenDown extends Instruction {

    public PenDown (int val) {
        super(val);
    }

    public PenDown (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, List<Variable> vars) {
        if (this.hasInnerInstruction()) {
            this.valueForExec = this.possibleInner.execute(t, vars);
        }
        t.setPenUp(false);
        return 1;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

package slogo.model;

import java.util.List;

public class PenUp extends Instruction {

    public PenUp (int val) {
        super(val);
    }

    public PenUp (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, List<Variable> vars) {
        if (this.hasInnerInstruction()) {
            this.valueForExec = this.possibleInner.execute(t, vars);
        }
        t.setPenUp(true);
        return 0;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

package slogo.model;

import java.util.Collection;
import java.util.List;

public class PenDown extends Instruction {

    public PenDown (int val) {
        super(val);
    }

    public PenDown (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, Collection<Variable> vars) {
        if (this.hasInnerInstruction()) {
            valueForExec = possibleInner.execute(t, vars);
        }
        t.setProperties(t.getXPos(), t.getYPos(), t.getAngle(), false);
        return 1;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

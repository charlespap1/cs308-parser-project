package slogo.model;

import java.util.Collection;
import java.util.List;

public class PenUp extends Instruction {

    public PenUp (int val) {
        super(val);
    }

    public PenUp (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, Collection<Variable> vars) {
        if (this.hasInnerInstruction()) {
            valueForExec = possibleInner.execute(t, vars);
        }
        t.setProperties(t.getXPos(), t.getYPos(), t.getAngle(), true);
        return 0;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

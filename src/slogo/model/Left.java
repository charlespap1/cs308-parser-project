package slogo.model;

import java.util.Collection;
import java.util.List;

public class Left extends Instruction {

    public Left (double val) {
        super(val);
    }

    public Left (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, Collection<Variable> vars) {
        if (this.hasInnerInstruction()) {
            valueForExec = possibleInner.execute(t, vars);
        }
        double angle = t.getAngle() + valueForExec;
        t.setProperties(t.getXPos(), t.getYPos(), angle, t.getIsPenUp());
        return valueForExec;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

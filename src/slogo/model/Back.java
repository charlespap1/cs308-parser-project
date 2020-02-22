package slogo.model;

import java.util.Collection;
import java.util.List;

public class Back extends Instruction {

    public Back (double val) {
        super(val);
    }

    public Back (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, Collection<Variable> vars) {
        if (this.hasInnerInstruction()) {
            valueForExec = possibleInner.execute(t, vars);
        }
        double x = t.getXPos() - valueForExec * Math.cos(t.getAngle());
        double y = t.getYPos() - valueForExec * Math.sin(t.getAngle());
        t.setProperties(x, y, t.getAngle(), t.getIsPenUp());
        return valueForExec;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

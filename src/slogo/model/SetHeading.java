package slogo.model;

import java.util.Collection;
import java.util.List;

public class SetHeading extends Instruction {

    public SetHeading (double val) {
        super(val);
    }

    public SetHeading (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, Collection<Variable> vars) {
        if (this.hasInnerInstruction()) {
            valueForExec = possibleInner.execute(t, vars);
        }
        double prevAngle = t.getAngle();
        t.setProperties(t.getXPos(), t.getYPos(), valueForExec, t.getIsPenUp());
        return Math.abs(t.getAngle() - prevAngle);
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

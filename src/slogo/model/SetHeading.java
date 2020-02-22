package slogo.model;

import java.util.List;

public class SetHeading extends Instruction {

    public SetHeading (double val) {
        super(val);
    }

    public SetHeading (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, List<Variable> vars) {
        if (this.hasInnerInstruction()) {
            this.valueForExec = this.possibleInner.execute(t, vars);
        }
        double prevAngle = t.getAngle();
        t.setAngle(this.valueForExec);
        return Math.abs(t.getAngle() - prevAngle);
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

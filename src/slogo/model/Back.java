package slogo.model;

import java.util.List;

public class Back extends Instruction {

    public Back (double val) {
        super(val);
    }

    public Back (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, List<Variable> vars) {
        if (this.hasInnerInstruction()) {
            this.valueForExec = this.possibleInner.execute(t, vars);
        }
        t.setxPos(t.getxPos() - valueForExec * Math.cos(t.getAngle()));
        t.setyPos(t.getyPos() - valueForExec * Math.sin(t.getAngle()));
        return valueForExec;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

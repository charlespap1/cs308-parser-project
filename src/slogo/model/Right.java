package slogo.model;

import java.util.List;

public class Right extends Instruction {

    public Right (double val) {
        super(val);
    }

    public Right (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, List<Variable> vars) {
        if (this.hasInnerInstruction()) {
            this.valueForExec = this.possibleInner.execute(t, vars);
        }
        t.setAngle(t.getAngle() - this.valueForExec);
        return valueForExec;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}
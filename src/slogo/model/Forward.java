package slogo.model;

import java.util.List;

public class Forward extends Instruction {

    public Forward (int val) {
        super(val);
    }

    public Forward (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public int execute (Turtle t, List<Variable> vars) {
        if (this.hasInnerInstruction()) {
            this.valueForExec = this.possibleInner.execute(t, vars);
        }
        t.setxPos(t.getxPos() + valueForExec * Math.cos(t.getAngle()));
        t.setyPos(t.getyPos() + valueForExec * Math.sin(t.getAngle()));
        return valueForExec;
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

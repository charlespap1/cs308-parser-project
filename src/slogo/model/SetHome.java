package slogo.model;

import java.util.List;

public class SetHome extends Instruction {

    public SetHome (double val) {
        super(val);
    }

    public SetHome (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, List<Variable> vars) {
        if (this.hasInnerInstruction()) {
            this.valueForExec = this.possibleInner.execute(t, vars);
        }
        double xPos = t.getxPos();
        double yPos = t.getyPos();
        t.setxPos(0);
        t.setyPos(0);
        return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

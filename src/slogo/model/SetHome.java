package slogo.model;

import java.util.Collection;
import java.util.List;

public class SetHome extends Instruction {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;

    public SetHome (double val) {
        super(val);
    }

    public SetHome (Instruction possibleInner) {
        super(possibleInner);
    }

    @Override
    public double execute (Turtle t, Collection<Variable> vars) {
        if (this.hasInnerInstruction()) {
            this.valueForExec = this.possibleInner.execute(t, vars);
        }
        double xPos = t.getXPos();
        double yPos = t.getYPos();
        t.setProperties(HOME_X, HOME_Y, t.getAngle(), t.getIsPenUp());
        return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
    }

    @Override
    public List<String> getNeededVarNames() {
        return null;
    }
}

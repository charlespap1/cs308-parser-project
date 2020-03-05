package slogo.model.tokens;

import java.util.List;

public class SetPenSize extends DisplayCommand {
    private static final int numArgs = 1;

    public SetPenSize(String name) {
        super(numArgs);
        instrName = name;
    }
}

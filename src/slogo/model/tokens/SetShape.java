package slogo.model.tokens;

import java.util.List;

public class SetShape extends DisplayCommand {
    private static final int numArgs = 1;

    public SetShape(String name) {
        super(numArgs);
        instrName = name;
    }
}

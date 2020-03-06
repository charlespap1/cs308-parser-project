package slogo.view.scrollers;

import slogo.model.tokens.Instruction;
import slogo.model.tokens.Token;
import slogo.view.SetInputAction;

public class CommandViewer extends ScrollingWindow {
    SetInputAction setter;

    public CommandViewer(double elementWidthFactor, double topPadding, SetInputAction action) {
        super(elementWidthFactor, topPadding);
        setter = action;
    }

    protected void onSelectedItem(Token t){
        StringBuilder output = new StringBuilder();
        output.append(t.toString());
        output.append(" 0".repeat(Math.max(0, ((Instruction) t).numRequiredArgs())));
        setter.setInput(output.toString());
    }
}

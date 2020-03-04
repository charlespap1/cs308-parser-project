package slogo.view.scrollers;

import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;
import slogo.view.SetInputAction;

public class CommandViewer extends ListViewer {
    SetInputAction setter;

    public CommandViewer(double elementWidthFactor, double topPadding, SetInputAction action) {
        super(elementWidthFactor, topPadding);
        setter = action;
        myList.setOnMouseClicked(e -> onSelectedItem(myList.getSelectionModel().getSelectedItem()));
    }

    protected void onSelectedItem(Token t){
        StringBuilder output = new StringBuilder();
        output.append(t.toString());
        output.append(" 0".repeat(Math.max(0, ((Instruction) t).numRequiredArgs())));
        setter.setInput(output.toString());
    }
}

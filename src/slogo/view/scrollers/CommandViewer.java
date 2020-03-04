package slogo.view.scrollers;

import slogo.view.SetInputAction;

public class CommandViewer extends ListViewer {
    public CommandViewer(double elementWidthFactor, double topPadding, SetInputAction action) {
        super(elementWidthFactor, topPadding);
        myList.setOnMouseClicked(e -> action.setInput(myList.getSelectionModel().getSelectedItem().toString()));
    }
}

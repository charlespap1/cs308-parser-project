package slogo.view.scrollers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import slogo.model.code.Token;
import slogo.model.code.Variable;

public class VariableViewer extends ListViewer {
    public VariableViewer(double elementWidthFactor, double topPadding) {
        super(elementWidthFactor, topPadding);
        myList.setOnMouseClicked(e -> {
            createInputBox(myList.getSelectionModel().getSelectedItem());
        });
    }

    private void createInputBox(Token t){
        HBox box = new HBox();
        Label label = new Label(t.toString());
        TextArea text = new TextArea();
        text.setMaxWidth(myWidth/2);
        text.setMaxHeight(20);
        Button b = new Button("set");
        b.setOnAction(e -> {
            ((Variable) t).setVariable(Double.parseDouble(text.getText()));
            myHolder.getChildren().remove(box);
        });
        box.getChildren().addAll(label, text, b);
        myHolder.getChildren().add(box);
    }
}

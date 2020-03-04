package slogo.view.scrollers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import slogo.model.code.Token;
import slogo.model.code.Variable;

public class VariableViewer extends ListViewer {
    private HBox box =  new HBox();
    private Button b;
    private Label label = new Label();
    private TextArea text;

    public VariableViewer(double elementWidthFactor, double topPadding) {
        super(elementWidthFactor, topPadding);
        buildHBox();
        myList.setOnMouseClicked(e -> {
            setBoxToVariable(myList.getSelectionModel().getSelectedItem());
        });
    }

    private void buildHBox(){
        text = new TextArea();
        text.setMaxWidth(myWidth/2);
        text.setMaxHeight(20);
        b = new Button("set");
        box.getChildren().addAll(label, text, b);
        myHolder.getChildren().add(box);
    }

    private void setBoxToVariable(Token t){
        label.setText(t.toString());
        b.setOnAction(e -> {
            ((Variable) t).setVariable(Double.parseDouble(text.getText()));
            myHolder.getChildren().remove(box);
        });
        if (!myHolder.getChildren().contains(box)) myHolder.getChildren().add(box);
    }
}

package slogo.view.scrollers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import slogo.model.code.Token;
import slogo.model.code.Variable;

public class VariableViewer extends ScrollingWindow {
    public static String BUTTON_TEXT = "set";
    private HBox box =  new HBox();
    private Button button = new Button(BUTTON_TEXT);
    private Label label = new Label();
    private TextArea text = new TextArea();

    public VariableViewer(double elementWidthFactor, double topPadding) {
        super(elementWidthFactor, topPadding);
        buildHBox();
    }

    private void buildHBox(){
        // TODO: make less ugly :)
        text.setMaxWidth(myWidth/2);
        text.setMaxHeight(20);
        box.getChildren().addAll(label, text, button);
    }

    @Override
    protected void onSelectedItem(Token t){
        label.setText(t.toString());
        button.setOnAction(e -> {
            ((Variable) t).setVariable(Double.parseDouble(text.getText()));
            myHolder.getChildren().remove(box);
        });
        if (!myHolder.getChildren().contains(box)) myHolder.getChildren().add(box);
    }
}

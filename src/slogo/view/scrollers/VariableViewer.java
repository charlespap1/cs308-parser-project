package slogo.view.scrollers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import slogo.model.tokens.Token;
import slogo.model.tokens.Variable;

public class VariableViewer extends ScrollingWindow {
    private static final String BUTTON_TEXT = "set";
    private static final int HBOX_SPACING = 10;
    private static final int TEXT_HEIGHT = 20;
    private HBox box =  new HBox(HBOX_SPACING);
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
        text.setMaxHeight(TEXT_HEIGHT);
        box.getChildren().addAll(label, text, button);
    }

    @Override
    protected void onSelectedItem(Token t){
        label.setText(t.toString());
        text.setText("");
        button.setOnAction(e -> {
            ((Variable) t).setVariable(Double.parseDouble(text.getText()));
            myHolder.getChildren().remove(box);
        });
        // TODO: deal with empty box
        if (!myHolder.getChildren().contains(box)) myHolder.getChildren().add(box);
    }
}

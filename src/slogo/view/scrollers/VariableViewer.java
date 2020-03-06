package slogo.view.scrollers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import slogo.model.tokens.Token;
import slogo.model.tokens.Variable;

import java.util.Objects;

/**
 * Class which allows users to view their previously defined variables and click
 * to change them
 */
public class VariableViewer extends ScrollingWindow {
    public static final String GO_BUTTON = "go.png";
    private static final int HBOX_SPACING = 10;
    private static final int TEXT_HEIGHT = 20;
    private HBox box =  new HBox(HBOX_SPACING);
    private Button button = new Button();
    private Label label = new Label();
    private TextArea text = new TextArea();

    public VariableViewer(double elementWidthFactor, double topPadding) {
        super(elementWidthFactor, topPadding);
        ImageView image = new ImageView(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(GO_BUTTON))));
        image.setPreserveRatio(true);
        image.setFitHeight(TEXT_HEIGHT);
        button.setGraphic(image);
        buildHBox();
    }

    private void buildHBox(){
        text.setMaxWidth(myWidth/2);
        text.setMaxHeight(TEXT_HEIGHT);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, text, button);
    }

    /**
     * Allows for clicking on variable to change it
     * @param t
     */
    @Override
    protected void onSelectedItem(Token t){
        label.setText(t.toString() + " " + t.execute());
        text.setText("");
        button.setOnAction(e -> {
            try{
                double newVal = Double.parseDouble(text.getText());
                ((Variable) t).setVariable(newVal);
            } catch (Exception ex){
                ((Variable) t).setVariable(t.execute());
            }
            myHolder.getChildren().remove(box);
        });
        if (!myHolder.getChildren().contains(box)) myHolder.getChildren().add(box);
    }
}

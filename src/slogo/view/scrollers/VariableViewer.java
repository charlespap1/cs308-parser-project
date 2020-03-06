package slogo.view.scrollers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import slogo.model.tokens.Token;
import slogo.model.tokens.Variable;

import java.beans.EventHandler;
import java.util.Objects;

/**
 * Class which allows users to view their previously defined variables and click
 * to change them
 */
public class VariableViewer extends ScrollingWindow {
    public static final String INNER_LIST_STYLE = "inner-list-view";
    public static final String GO_BUTTON = "go.png";
    private static final int HBOX_SPACING = 10;
    private static final int TEXT_HEIGHT = 20;
    private HBox box =  new HBox(HBOX_SPACING);
    private Button button = new Button();
    private Label label = new Label();
    private TextArea text = new TextArea();
    private ObservableList<Double> myValues = FXCollections.observableArrayList();
    private ListView<Double> valuesListView = new ListView<>(myValues);

    public VariableViewer(double elementWidthFactor, double topPadding) {
        super(elementWidthFactor, topPadding);
        ImageView image = new ImageView(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(GO_BUTTON))));
        image.setPreserveRatio(true);
        image.setFitHeight(TEXT_HEIGHT);
        button.setGraphic(image);
        buildHBox();
        myView.getChildren().add(valuesListView);
    }

    private void buildHBox(){
        text.setMaxWidth(myWidth/2);
        text.setMaxHeight(TEXT_HEIGHT);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, text, button);
    }

    @Override
    public void bindList(ObservableList<Token> list) {
        super.bindList(list);
        list.addListener((ListChangeListener<Token>) c -> {
            myValues.clear();
            for (Token t:list){
                myValues.add(t.execute());
            }
        });
        Node n1 = valuesListView.lookup(".scroll-bar");
        if (n1 instanceof ScrollBar) {
            final ScrollBar bar1 = (ScrollBar) n1;
            bar1.setVisibleAmount(0.0);
            Node n2 = myList.lookup(".scroll-bar");
            if (n2 instanceof ScrollBar) {
                final ScrollBar bar2 = (ScrollBar) n2;
                bar1.valueProperty().bindBidirectional(bar2.valueProperty());
            }
        }
        myList.getStyleClass().add(INNER_LIST_STYLE);
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

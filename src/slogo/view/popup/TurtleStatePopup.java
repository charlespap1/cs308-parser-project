package slogo.view.popup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class TurtleStatePopup {
    public static final int HOLDER_SPACING = 5;
    public static final int WINDOW_WIDTH = 340;
    public static final int WINDOW_HEIGHT = 50;
    public static final int LIST_WIDTH = 165;
    public static final Color HOLDER_BACKGROUND_COLOR = Color.LAVENDER;
    public static final int HOLDER_PADDING = 5;
    private static final int LIST_HEIGHT = 20;
    public static final int EXIT_BUTTON_SIZE = 5;
    private static final String X_IMAGE_FILE = "exit.png";

    private Popup myPopup;

    private HBox myHolder;
    private Text myPrompt;
    private Button exit;
    private Button myGo;

    public TurtleStatePopup(){
        myPopup = new Popup();

        myHolder = new HBox(HOLDER_SPACING);

        myHolder.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        myHolder.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        myHolder.setBackground(new Background(new BackgroundFill(HOLDER_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        myGo = new Button("Go");
        exit = new Button("Exit");

        exit.setOnAction(event -> myPopup.hide());

        myHolder.setAlignment(Pos.CENTER);
        myHolder.setPadding(new Insets(HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING));

        myHolder.getChildren().addAll(exit, myGo);

        myPopup.getContent().add(myHolder);
    }

    public void show(Stage currentStage){
        myPopup.show(currentStage);
    }

    public void hide(Stage currentStage){
        myPopup.hide();
    }
}

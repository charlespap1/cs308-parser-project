package slogo.view.popup;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class LoadConfigPopup {

  public static final int HOLDER_SPACING = 10;
  public static final int WINDOW_WIDTH = 300;
  public static final int WINDOW_HEIGHT = 50;
  public static final int TEXT_FIELD_WIDTH = 125;
  public static final Color HOLDER_BACKGROUND_COLOR = Color.LAVENDER;
  public static final int HOLDER_PADDING = 10;

  private Popup myPopup;

  private HBox myHolder;
  private TextField myInput;
  private Text myPrompt;
  private Button myLoad;

  public LoadConfigPopup()
  {
    myPopup = new Popup();

    myHolder = new HBox(HOLDER_SPACING);

    myHolder.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    myHolder.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    myHolder.setBackground(new Background(new BackgroundFill(HOLDER_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

    myInput = new TextField();
    myInput.setMaxWidth(TEXT_FIELD_WIDTH);
    myPrompt = new Text();
    myPrompt.setText("    Enter \na filename: ");

    setButton();

    myHolder.setAlignment(Pos.CENTER);
    myHolder.setPadding(new Insets(HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING));
    myHolder.getChildren().addAll(myPrompt, myInput, myLoad);

    myPopup.getContent().add(myHolder);

  }

  private void setButton() {
    myLoad = new Button("Load");
    //myLoad.setOnAction(e -> myPopup.hide());
  }

  /*public void setPromptProperty(StringProperty sp){
    myPrompt.textProperty().bind(sp);
    myPrompt.textProperty().setValue("Enter a filename");
    myHolder.getChildren().add(0, myPrompt);
  }*/

  public Popup getMyPopup()
  {
    return myPopup;
  }

  public void setLoadButton(EventHandler<ActionEvent> load)
  {
    myLoad.setOnAction(load);
  }

}

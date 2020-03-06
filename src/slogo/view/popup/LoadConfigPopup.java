package slogo.view.popup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class LoadConfigPopup implements ViewPopup{

  public static final int HOLDER_SPACING = 10;
  public static final int WINDOW_WIDTH = 325;
  public static final int WINDOW_HEIGHT = 50;
  public static final int TEXT_FIELD_WIDTH = 125;
  public static final int EXIT_BUTTON_SIZE = 5;
  public static final Color HOLDER_BACKGROUND_COLOR = Color.LAVENDER;
  public static final int HOLDER_PADDING = 10;
  public static final String PACKAGE = "resources/%s.txt";
  private static final String X_IMAGE_FILE = "exit.png";

  private Popup myPopup;

  private HBox myHolder;
  private TextField myInput;
  private Text myPrompt;
  private Button myLoad;
  private Button exit;

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

    setButtons();

    myHolder.setAlignment(Pos.CENTER);
    myHolder.setPadding(new Insets(HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING));
    myHolder.getChildren().addAll(exit, myPrompt, myInput, myLoad);

    myPopup.getContent().add(myHolder);

  }

  private void setButtons() {
    myLoad = new Button("Load");

    exit = new Button();
    exit.setLayoutX(myHolder.getLayoutX());
    exit.setLayoutY(myHolder.getLayoutY());
    exit.setMaxSize( EXIT_BUTTON_SIZE,EXIT_BUTTON_SIZE);
    exit.setOnAction(e -> myPopup.hide());
    ImageView exitImage = getButtonPic();
    exit.setGraphic(exitImage);

  }

  private ImageView getButtonPic()
  {
    Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(X_IMAGE_FILE)));
    ImageView iv = new ImageView(image);
    iv.setFitWidth(EXIT_BUTTON_SIZE);
    iv.setFitHeight(EXIT_BUTTON_SIZE);
    iv.setPreserveRatio(true);
    return iv;
  }


  public Popup getMyPopup()
  {
    return myPopup;
  }

  public void setPopupButton(EventHandler<ActionEvent> e)
  {
    myLoad.addEventHandler(ActionEvent.ACTION, e);
    myLoad.addEventHandler(ActionEvent.ACTION, h -> myPopup.hide());
  }

  public File getFile() throws FileDoesNotExistException
  {
    String path = String.format(PACKAGE, myInput.getText());
    try {
      File f = new File(path);
      Scanner myReader = new Scanner(f);
      return f;

    } catch (Exception e) {
        throw new FileDoesNotExistException(e);
    }
  }


}

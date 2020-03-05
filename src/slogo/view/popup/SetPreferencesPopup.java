package slogo.view.popup;

import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class SetPreferencesPopup implements ViewPopup{

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
  private ComboBox<String> myList;
  private Button exit;

  private Button myGo;


  public SetPreferencesPopup()
  {
    myPopup = new Popup();

    myHolder = new HBox(HOLDER_SPACING);

    myHolder.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    myHolder.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    myHolder.setBackground(new Background(new BackgroundFill(HOLDER_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

    myPrompt = new Text();
    myPrompt.setText("    Choose \n Preference: ");

    myGo = new Button("Go");

    myHolder.setAlignment(Pos.CENTER);
    myHolder.setPadding(new Insets(HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING));

    myPopup.getContent().add(myHolder);

    setList();
    setExit();
    myList.setPrefSize(LIST_WIDTH, LIST_HEIGHT);
    myHolder.getChildren().addAll(exit, myPrompt, myList, myGo);

  }

  private void setExit()
  {
    exit = new Button();
    exit.setLayoutX(myHolder.getLayoutX());
    exit.setLayoutY(myHolder.getLayoutY());
    exit.setMaxSize( EXIT_BUTTON_SIZE,EXIT_BUTTON_SIZE);
    exit.setOnAction(e -> myPopup.hide());
    ImageView exitImage = getButtonPic();
    exit.setGraphic(exitImage);
  }


  private void setList()
  {
    myList = new ComboBox<>();
    ObservableList<String> items = FXCollections.observableArrayList(
        "DefaultPreferences", "PreferencesOne");
    myList.setValue("DefaultPreferences");
    myList.setItems(items);
  }

  public String getPreference()
  {
    return myList.getSelectionModel().getSelectedItem();
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

  @Override
  public void setPopupButton(EventHandler<ActionEvent> makeNewWindow) {
    myGo.setOnAction(makeNewWindow);
  }

  public Popup getMyPopup()
  {
    return myPopup;
  }

}

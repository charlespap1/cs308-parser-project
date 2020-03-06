package slogo.view.popup;

import java.util.Objects;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * Basic design that popups use
 * Includes exit, prompt and go button
 * @author Juliet
 */
public class PopupSkeleton implements ViewPopup{

  public static final int HOLDER_SPACING = 5;
  public static final int WINDOW_WIDTH = 340;
  public static final int WINDOW_HEIGHT = 50;
  public static final Color HOLDER_BACKGROUND_COLOR = Color.LAVENDER;
  public static final int HOLDER_PADDING = 5;
  public static final int EXIT_BUTTON_SIZE = 5;
  private static final String X_IMAGE_FILE = "exit.png";

  protected Popup myPopup;

  protected HBox myHolder;
  protected Text myPrompt;
  protected Button myExit;

  protected Button myGo;

  public PopupSkeleton()
  {
    myPopup = new Popup();

    myHolder = new HBox(HOLDER_SPACING);

    myHolder.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    myHolder.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    myHolder.setBackground(new Background(new BackgroundFill(HOLDER_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

    myPrompt = new Text();

    myHolder.setAlignment(Pos.CENTER);
    myHolder.setPadding(new Insets(HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING,HOLDER_PADDING));

    setButtons();
    myHolder.getChildren().addAll(myExit, myPrompt, myGo);

    myPopup.getContent().add(myHolder);
  }

  /**
   * Returns popup to be called with popup.show() on the stage given within
   * the SetupScreen class
   * @return
   */
  public Popup getMyPopup()
  {
    return myPopup;
  }

  /**
   * Binds the text of the go button to dynamically change with different language
   * @param sp
   */
  @Override
  public void setGoButtonProperty(StringProperty sp) {
    myGo.textProperty().bind(sp);
  }

  /**
   * Binds the text of the prompt to dynamically change with different language
   * @param sp
   */
  @Override
  public void setPromptProperty(StringProperty sp) {
    myPrompt.textProperty().bind(sp);
  }

  /**
   * Sets the popup go button with whatever event is passed and then hides the popup
   * @param event
   */
  public void setPopupButton(EventHandler<ActionEvent> event) {
    myGo.addEventHandler(ActionEvent.ACTION, event);
    myGo.addEventHandler(ActionEvent.ACTION, e -> myPopup.hide());
  }

  private void setButtons() {

    myGo = new Button();

    myExit = new Button();
    myExit.setLayoutX(myHolder.getLayoutX());
    myExit.setLayoutY(myHolder.getLayoutY());
    myExit.setMaxSize( EXIT_BUTTON_SIZE,EXIT_BUTTON_SIZE);
    myExit.setOnAction(e -> myPopup.hide());
    ImageView exitImage = getButtonPic();
    myExit.setGraphic(exitImage);
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

}

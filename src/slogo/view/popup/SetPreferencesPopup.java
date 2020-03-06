package slogo.view.popup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * This popup allows for users to make a new window and specify preferences to go with it
 */
public class SetPreferencesPopup extends PopupSkeleton implements ViewPopup{

  public static final int LIST_WIDTH = 165;
  private static final int LIST_HEIGHT = 20;

  private ComboBox<String> myList;

  public SetPreferencesPopup()
  {
    super();
    myPrompt.setText("    Choose \n Preference: ");
    setList();
    myList.setPrefSize(LIST_WIDTH, LIST_HEIGHT);
    myHolder.getChildren().add(2, myList);

  }

  /**
   * Gets whatever the preference the user chose and gives it to the
   * Controller to call makeNewWindow()
   * @return
   */
  public String getPreference()
  {
    return myList.getSelectionModel().getSelectedItem();
  }

  private void setList()
  {
    myList = new ComboBox<>();
    ObservableList<String> items = FXCollections.observableArrayList(
        "DefaultPreferences", "PreferencesOne");
    myList.setValue("DefaultPreferences");
    myList.setItems(items);
  }


}

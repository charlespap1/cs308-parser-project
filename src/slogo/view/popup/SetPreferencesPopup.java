package slogo.view.popup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * This popup allows for users to make a new window and specify preferences to go with it
 *
 * @author Juliet
 */
public class SetPreferencesPopup extends PopupSkeleton implements ViewPopup {

    private static final int LIST_WIDTH = 165;
    private static final int LIST_HEIGHT = 20;
    private static final String RESOURCE_PACKAGE = "resources.preferences.PreferenceMap";
    private static final String DEFAULT_KEY = "DefaultPreferences";
    private ResourceBundle myPreferenceMap = ResourceBundle.getBundle(RESOURCE_PACKAGE);
    private Map<String, String> myPreferenceTranslations;


    private ComboBox<String> myList;

    public SetPreferencesPopup() {
        super();
        setList();
        myList.setPrefSize(LIST_WIDTH, LIST_HEIGHT);
        myHolder.getChildren().add(2, myList);
    }

    /**
     * Gets whatever the preference the user chose and gives it to the
     * Controller to call makeNewWindow()
     *
     * @return
     */
    public String getPreference() {
        return getKey(myList.getSelectionModel().getSelectedItem());
    }

    private void setList() {
        myList = new ComboBox<>();
        myPreferenceTranslations = new HashMap<>();

        ObservableList<String> items = FXCollections.observableArrayList();
        for(String key: myPreferenceMap.keySet())
        {
            String visualTranslation = myPreferenceMap.getString(key);
            myPreferenceTranslations.put(key, visualTranslation);
            items.add(visualTranslation);
        }

        myList.setValue(myPreferenceMap.getString(DEFAULT_KEY));
        myList.setItems(items);
    }

    private String getKey(String value)
    {
        for(String key: myPreferenceMap.keySet())
        {
            if(myPreferenceMap.getString(key) == value)
            {
                return key;
            }
        }
        return DEFAULT_KEY;
    }

}

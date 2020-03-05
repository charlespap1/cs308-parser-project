package slogo.view.selectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Collection;
import java.util.List;
import javafx.scene.text.Text;

/**
 * This structure allows the user to code in different language environments
 * Technically doesn't extend color selector but fits in the idea of the package
 * @author Natalie
 */

public class LanguageSelector {
    public static final Collection<String> LANGUAGES = List.of("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu");
    public static final ObservableList<String> LANGUAGE_LIST = FXCollections.observableArrayList(LANGUAGES);
    public static final String DEFAULT_LANGUAGE = "English";

    private Text myTitle = new Text();
    private HBox box;
    private StringProperty currentLanguage = new SimpleStringProperty();

    public LanguageSelector(double x, double y){
        box = new HBox();
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setAlignment(Pos.CENTER);
        makeSelector();
    }

    /**
     * Allows for adding the selector to the root
     * @return
     */
    public Node getView(){ return box; }

    /**
     * Allows us to get the current language for property binding
     * @return
     */
    public StringProperty getLanguageChoiceProperty() { return currentLanguage; }

    private void makeSelector(){
        ComboBox<String> languages = new ComboBox<>(LANGUAGE_LIST);
        languages.valueProperty().setValue(DEFAULT_LANGUAGE);
        currentLanguage.bind(languages.valueProperty());
        box.getChildren().add(languages);
    }

    public void setTitleProperty(StringProperty  sp){
        myTitle.textProperty().bind(sp);
        box.getChildren().add(0, myTitle);
    }
}

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

public class LanguageSelector {
    public static final Collection<String> LANGUAGES = List.of("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu");
    public static final ObservableList<String> LANGUAGE_LIST = FXCollections.observableArrayList(LANGUAGES);
    public static final String DEFAULT_LANGUAGE = "English";
    // TODO: hard coded text
    public static final String PROMPT = "Select a language:   ";

    private HBox box;
    private StringProperty currentLanguage = new SimpleStringProperty();

    public LanguageSelector(double x, double y){
        box = new HBox();
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setAlignment(Pos.CENTER);
        makeSelector();
    }

    public Node getView(){ return box; }

    public StringProperty getLanguageChoiceProperty() { return currentLanguage; }

    private void makeSelector(){
        Label prompt = new Label(PROMPT);
        ComboBox<String> languages = new ComboBox<>(LANGUAGE_LIST);
        languages.valueProperty().setValue(DEFAULT_LANGUAGE);
        currentLanguage.bind(languages.valueProperty());
        box.getChildren().addAll(prompt, languages);
    }
}

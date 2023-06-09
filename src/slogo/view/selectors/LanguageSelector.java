package slogo.view.selectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import slogo.view.StaticViewElement;

import java.util.Collection;
import java.util.List;

/**
 * This structure allows the user to code in different language environments
 * Technically doesn't extend color selector but fits in the idea of the package
 *
 * @author Natalie
 */

public class LanguageSelector implements StaticViewElement {
    private static final Collection<String> LANGUAGES = List.of("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu");
    private static final ObservableList<String> LANGUAGE_LIST = FXCollections.observableArrayList(LANGUAGES);
    private static final String DEFAULT_LANGUAGE = "English";
    private static final int FIRST_ELEMENT_IN_LIST = 0;

    private Text myTitle = new Text();
    private HBox box;
    private StringProperty currentLanguage = new SimpleStringProperty();

    public LanguageSelector(double x, double y) {
        box = new HBox();
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setAlignment(Pos.CENTER);
        makeSelector();
    }

    /**
     * Allows for adding the selector to the root
     *
     * @return
     */
    @Override
    public Node getView() {
        return box;
    }

    /**
     * Allows us to get the current language for property binding
     *
     * @return
     */
    public StringProperty getLanguageChoiceProperty() {
        return currentLanguage;
    }

    /**
     * The language prompt itself has to be able to dynamically change with the given language
     */
    @Override
    public void setTitleProperty(List<StringProperty> sp) {
        myTitle.textProperty().bind(sp.get(FIRST_ELEMENT_IN_LIST));
        box.getChildren().add(FIRST_ELEMENT_IN_LIST, myTitle);
    }

    private void makeSelector() {
        ComboBox<String> languages = new ComboBox<>(LANGUAGE_LIST);
        languages.valueProperty().setValue(DEFAULT_LANGUAGE);
        currentLanguage.bind(languages.valueProperty());
        box.getChildren().add(languages);
    }
}

package slogo.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pop up window that displays all of the currently active turtles
 */
public class turtlePopUpWindow {
    private Text myTitle = new Text();
    private HBox box;

    /**
     * Create a new turtle pop up window at the given x and y
     * @param x: x position
     * @param y: y position
     */
    public turtlePopUpWindow(double x, double y){
        box = new HBox();
        box.setLayoutX(x);
        box.setLayoutY(y);
        //Label prompt = new Label(PROMPT);
        ObservableList<String>  myList = FXCollections.observableArrayList(List.of("1", "2", "3"));
        ComboBox<String> languages = new ComboBox<>(myList);
        box.getChildren().add(languages);
    }

    /**
     * given a list of turtles, changes teh shown list to display their values
     * @param turtles
     */
    public void setShownTurtles(List<Turtle> turtles){
        for (Turtle t : turtles){

        }
    }
}

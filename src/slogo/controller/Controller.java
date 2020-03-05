package slogo.controller;

import java.util.ResourceBundle;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import slogo.model.Model;
import slogo.model.Turtle;
import slogo.view.Interactions;
import slogo.view.DisplayAction;
import slogo.view.popup.LoadConfigPopup;

/**
 * Main method where the GUI comes together
 * @author natalie
 */
public class Controller extends Application {
    public static final String RESOURCES_PATH = "resources.commands.Methods";

    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Allows us to set up the initial stage and animation
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        makeWindow(primaryStage);
    }

    private void makeNewWindow() {
        makeWindow(new Stage());
    }

    private void showPopUp(Stage currentStage, Model myModel){
        LoadConfigPopup popup = new LoadConfigPopup();
        popup.getMyPopup().show(currentStage);
        EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                executeTextFile(popup.getFile(), myModel);
                popup.getMyPopup().hide();
            }
        };
        popup.setPopupButton(e);
    }

    private void makeWindow(Stage stage){
        Interactions myView = new Interactions(stage);
        Model myModel = new Model(myView.getLanguageChoice());
        myView.setGoButton(e -> getInstruction(myView, myModel));
        myView.setViewLists(myModel.getVariableList(), myModel.getNewCommandsList());
        myView.setErrorMessage(myModel.getErrorMessage());
        myView.setNewWindowButton(e -> makeNewWindow());
        setupCommands(myView, myModel);
        myModel.setAddTurtleFunction(myView::addTurtle);
        myView.setPopupButton(e -> showPopUp(stage, myModel));
    }




    /**
     * Method which can be called by any instance of a Visual object
     * and allows the caller to get the user input from the command input field
     * @return
     * @throws NullPointerException
     */
    private void getInstruction(Interactions view, Model model) throws NullPointerException {
        String input = view.getInstruction();
        model.executeCode(input);
    }

    private void setupCommands(Interactions view, Model model) {
        ResourceBundle rb = ResourceBundle.getBundle(RESOURCES_PATH);
        for (String key : rb.keySet()) {
            String methodName = rb.getString(key);
            DisplayAction action = view.getAction(methodName);
            model.setAction(key, action);
        }
    }

    private void executeTextFile(File f, Model model) throws NullPointerException {
        model.executeCode(f);
    }
}

package slogo.controller;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import slogo.model.Model;
import slogo.model.code.instructions.queries.YCor;
import slogo.view.Interactions;
import slogo.view.popup.LoadConfigPopup;

/**
 * Main method where the GUI comes together
 * @author natalie
 */
public class Controller extends Application {

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
                getTextFile(popup.getFile(), myModel);
                popup.getMyPopup().hide();
            }
        };
        popup.setPopupButton(e);
    }

    private void makeWindow(Stage stage){
        Interactions myView = new Interactions(stage);
        Model myModel = new Model(myView.getLanguageChoice());
        myView.setInitialTurtle(myModel.getTurtle());
        myView.setGoButton(e -> getInstruction(myView, myModel));
        myView.setViewLists(myModel.getVariableList(), myModel.getNewCommandsList());
        myView.setErrorMessage(myModel.getErrorMessage());
        myView.setNewWindowButton(e -> makeNewWindow());
        myView.setPopupButton(e -> showPopUp(stage, myModel));
        myModel.setClearAction(myView.getClearAction());
        //TODO: add listener for method tell command
    }




    /**
     * Method which can be called by any instance of a Visual object
     * and allows the caller to get the user input from the command input field
     * @return
     * @throws NullPointerException
     */
    private void getInstruction(Interactions view, Model model) throws NullPointerException {
        // TODO: error handling when receiving null pointer or if execute code throws error
        String input = view.getInstruction();
        model.executeCode(input);
    }


    private void getTextFile(File f, Model model) throws NullPointerException {
        model.executeCode(f);
    }
}

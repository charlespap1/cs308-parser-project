package slogo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.Model;
import slogo.model.code.instructions.queries.YCor;
import slogo.view.Interactions;

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

    private void makeWindow(Stage stage){
        Interactions myView = new Interactions(stage);
        Model myModel = new Model(myView.getLanguageChoice());
        myView.setTurtle(myModel.getTurtle());
        myView.setGoButton(e -> getInstruction(myView, myModel));
        myView.setViewLists(myModel.getVariableList(), myModel.getNewCommandsList());
        myView.setErrorMessage(myModel.getErrorMessage());
        myView.setNewWindowButton(e -> makeNewWindow());
        myModel.setClearAction(myView.getClearAction());
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
}

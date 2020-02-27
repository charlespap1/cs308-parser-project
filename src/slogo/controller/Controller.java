package slogo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.Model;
import slogo.view.Interactions;

/**
 * Main method where the GUI comes together
 * @author natalie
 */
public class Controller extends Application {

    private Model myModel;
    private Interactions myView;

    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Allows us to set up the initial stage and animation
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        myView = new Interactions(primaryStage);
        myModel = new Model(myView.getLanguageChoice());
        myView.setTurtle(myModel.getTurtle());
        myView.setGoButton(e -> getInstruction());
        myView.setViewLists(myModel.getVariableList(), myModel.getNewCommandsList());
        myView.setErrorMessage(myModel.getErrorMessage());
        myModel.setClearAction(myView.getClearAction());
    }

    /**
     * Method which can be called by any instance of a Visual object
     * and allows the caller to get the user input from the command input field
     * @return
     * @throws NullPointerException
     */
    private void getInstruction() throws NullPointerException {
        // TODO: error handling when receiving null pointer or if execute code throws error
        String input = myView.getInstruction();
        myModel.executeCode(input);
    }
}

package slogo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.Model;
import slogo.model.Turtle;
import slogo.view.Interactions;

/**
 * Main method where the GUI comes together
 * @author natalie
 */
public class Controller extends Application {
    ButtonAction goButtonAction = () -> getInstruction();

    private Model myModel;
    private Interactions myView;
    /**
     * Allows us to set up the initial stage and animation
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        myModel = new Model();
        myView = new Interactions(primaryStage);
        Turtle myTurtle = myModel.getTurtle();
        myView.setProperties(myTurtle);
        // this allows us to set the onclick action for the go button to be the getInstruction method in Controller,
        // prevents us from having to give the View access to the Controller
        myView.setGoButton(goButtonAction);
        myView.setViewLists(myModel.getVariableList(), myModel.getNewCommandsList());
    }


    /**
     * Method which can be called by any instance of a Visual object
     * and allows the caller to get the user input from the command input field
     * @return
     * @throws NullPointerException
     */
    public void getInstruction() throws NullPointerException {
        //make this get called when button is pressed in front end -- set some kind of event listener? ask TA
        String input = myView.getInstruction();
        myModel.executeCode(input);
    }

    public static void main (String[] args) {
        launch(args);
    }

}

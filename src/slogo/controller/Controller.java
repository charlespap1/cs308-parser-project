package slogo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.Model;
import slogo.model.tokens.Instruction;
import slogo.view.DisplayAction;
import slogo.view.Interactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Main method where the GUI comes together. Builds initial stage/window of program and any additional windows
 * created, including connecting necessary elements of front and back end.
 *
 * @author Natalie
 */
public class Controller extends Application {
    private static final String RESOURCES_PATH = "resources.commands.Methods";
    private static final String DEFAULT_PREFERENCES = "DefaultPreferences";

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Allows us to set up the initial stage and animation.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        makeWindow(primaryStage, DEFAULT_PREFERENCES);
    }

    private void makeNewWindow(String preference) {
        makeWindow(new Stage(), preference);
    }

    private void makeWindow(Stage stage, String preferences) {
        Interactions myView = new Interactions(stage);
        Model myModel = new Model(myView.getLanguageChoice());
        myView.setGoButton(e -> getInstruction(myView, myModel));
        myView.setViewLists(myModel.getVariableList(), myModel.getNewCommandsList());
        myView.setupHistory(myModel.getHistoryList(), myModel.getUndoDisabled(), myModel.getRedoDisabled());
        myView.setErrorMessage(myModel.getErrorMessage());
        myView.setNewWindowButton(e -> makeNewWindow(myView.getNewWindowPreferences()), stage);
        setupDisplayCommands(myView, myModel);
        myModel.setAddTurtleFunction(myView::addTurtle);
        myView.setPreferences(preferences);
        myView.setUndoAction(e -> myModel.undo());
        myView.setRedoAction(e -> myModel.redo());
        myView.setLoadTextFileButton(e -> myModel.executeCode(myView.getFile()), stage);
        myView.setClearHistory(e -> myModel.clearHistory());
        myView.setDirectInstructionExecutor(new DirectExecutor() {
            public void execute(Instruction i) {
                myModel.executeCode(i);
            }
            public void execute(String s) {
                myModel.executeCode(s);
            }
        });
        myView.setSaveTextFileButton(stage);
        myView.setSaveVarsAndCommandsButton(e -> myView.pushVarsAndCommandsToFile(myModel.getNewVarsAndCommandsAsString(), stage));
        myView.setLoadVarsAndCommandsButton(e -> myModel.executeCode(myView.getFile()), stage);
    }

    /**
     * Method which can be called by any instance of a Visual object
     * and allows the caller to get the user input from the command input field
     *
     * @return
     * @throws NullPointerException
     */
    private void getInstruction(Interactions view, Model model) throws NullPointerException {
        String input = view.getInstruction();
        if (input.length() > 0) model.executeCode(input);
    }

    private void setupDisplayCommands(Interactions view, Model model) {
        ResourceBundle rb = ResourceBundle.getBundle(RESOURCES_PATH);
        for (String key : rb.keySet()) {
            String methodName = rb.getString(key);
            DisplayAction action = view.getAction(methodName);
            model.setAction(key, action);
        }
    }
}

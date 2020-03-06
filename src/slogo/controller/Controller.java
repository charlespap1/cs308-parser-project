package slogo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.Model;
import slogo.model.tokens.Instruction;
import slogo.model.tokens.Token;
import slogo.view.DisplayAction;
import slogo.view.Interactions;
import slogo.view.popup.SetPreferencesPopup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Main method where the GUI comes together
 * @author natalie
 */
public class Controller extends Application {
    public static final String RESOURCES_PATH = "resources.commands.Methods";
    public static final String DEFAULT_PREFERENCES = "DefaultPreferences";
    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Allows us to set up the initial stage and animation
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

    private void makeWindow(Stage stage, String preferences){
        Interactions myView = new Interactions(stage);
        Model myModel = new Model(myView.getLanguageChoice());
        myView.setGoButton(e -> getInstruction(myView, myModel));
        myView.setViewLists(myModel.getVariableList(), myModel.getNewCommandsList());
        myView.setupHistory(myModel.getHistoryList(), myModel.getUndoDisabled(), myModel.getRedoDisabled());
        myView.setErrorMessage(myModel.getErrorMessage());
        myView.setNewWindowButton(e -> makeNewWindow(myView.getNewWindowPreferences()), stage);
        myView.setTurtlesStateButton(e -> showActiveTurtles(myView, stage));
        setupCommands(myView, myModel);
        myModel.setAddTurtleFunction(myView::addTurtle);
        myView.setPreferences(preferences);
        myView.setUndoAction(e -> myModel.undo());
        myView.setRedoAction(e -> myModel.redo());
        myView.setLoadTextFileButton(e -> executeTextFile(myView.getFile(), myView, myModel), stage);
        myView.setClearHistory(e -> myModel.clearHistory());
        myView.setDirectInstructionExecutor(new DirectExecutor() {
            public void execute(Instruction i) { myModel.executeCode(i); }
            public void execute(String s) { myModel.executeCode(s); }
        });
        myView.setSaveTextFileButton(stage);
    }



    private void showActiveTurtles(Interactions myView, Stage currentStage) {
        myView.myTurtleStatePopup.show(currentStage);
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

    private void executeTextFile(File f, Interactions view, Model model) throws NullPointerException {
        // print to see if working
        try {
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        model.executeCode(f);
    }
}

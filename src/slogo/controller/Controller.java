package slogo.controller;

import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import slogo.model.Model;
import slogo.model.Turtle;
import slogo.view.Interactions;
import slogo.view.DisplayAction;
import slogo.view.popup.FileDoesNotExistException;
import slogo.view.popup.LoadConfigPopup;
import slogo.view.popup.SetPreferencesPopup;

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
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        makeWindow(primaryStage, DEFAULT_PREFERENCES);
    }

    private void makeNewWindow(String preference) {
        makeWindow(new Stage(), preference);
    }

    private void showPopUp(Stage currentStage, Model myModel){
        //TODO: put front end back in front end
        LoadConfigPopup popup = new LoadConfigPopup();
        popup.getMyPopup().show(currentStage);
        EventHandler<ActionEvent> e = event -> {
            try{
                File commandFile = popup.getFile();
                executeTextFile(commandFile, myModel);
            }catch(FileDoesNotExistException err)
            {
                myModel.setErrorMessage(err.getMessage());
            }
            popup.getMyPopup().hide();
        };
        popup.setPopupButton(e);
    }

    private void makeWindow(Stage stage, String preferences){
        Interactions myView = new Interactions(stage, preferences);
        Model myModel = new Model(myView.getLanguageChoice());
        myView.setGoButton(e -> getInstruction(myView, myModel));
        myView.setViewLists(myModel.getVariableList(), myModel.getNewCommandsList());
        myView.setupHistory(myModel.getHistoryList(), myModel.getUndoDisabled(), myModel.getRedoDisabled());
        myView.setErrorMessage(myModel.getErrorMessage());
        myView.setNewWindowButton(e -> getNewPreferences(stage));
        myView.setTurtlesStateButton(e -> showActiveTurtles(myView, stage));
        setupCommands(myView, myModel);
        myModel.setAddTurtleFunction(myView::addTurtle);
        myView.setPopupButton(e -> showPopUp(stage, myModel));
        myView.setUndoAction(e -> myModel.undo());
        myView.setRedoAction(e -> myModel.redo());
    }


    private void showActiveTurtles(Interactions myView, Stage currentStage) {
        myView.myTurtleStatePopup.show(currentStage);
    }

    private void getNewPreferences(Stage currentStage) {
        SetPreferencesPopup prefPopup = new SetPreferencesPopup();
        prefPopup.getMyPopup().show(currentStage);

        prefPopup.setPopupButton(e -> {
            makeNewWindow(prefPopup.getPreference());
            prefPopup.getMyPopup().hide();
        });
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
            e.printStackTrace();
        }
        model.executeCode(f);
    }
}

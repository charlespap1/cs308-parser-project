package slogo.model.parse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.controller.AddNewTurtleFunction;
import slogo.model.Turtle;
import slogo.model.code.NewCommandName;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.exceptions.LanguageFileNotFoundException;
import slogo.model.code.exceptions.SyntaxException;
import slogo.model.code.instructions.*;
import slogo.model.code.instructions.commands.TurtleCommand;
import slogo.model.code.instructions.display.DisplayCommand;
import slogo.model.code.instructions.misc.To;
import slogo.model.code.instructions.multipleturtles.MultiTurtleCommand;
import slogo.model.code.instructions.queries.QueryCommand;
import slogo.view.DisplayAction;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class CodeFactory {
    public static String VARIABLE_TYPE = "Variable";
    public static String NEW_COMMAND_TYPE = "Command";
    public static String TO_TYPE = "MakeUserInstruction";

    private RegexHandler keyGrabber;
    private Map<String, Class> mappings = new HashMap<>();
    private Map<String, NewCommand> newCommandMap = new HashMap<>();
    private Map<String, Variable> variableMap = new HashMap<>();
    private ObservableList<String> vars = FXCollections.observableArrayList();
    private ObservableList<String> newCommands = FXCollections.observableArrayList();
    private Map<String, DisplayAction> setActionMap = new HashMap<>();
    private Map<Integer, Turtle> turtleMap = new HashMap<>();
    private List<Turtle> activeTurtles = new ArrayList<>();
    private AddNewTurtleFunction addTurtleFunction;

    public CodeFactory(String language) throws LanguageFileNotFoundException {
        setLanguage(language);
    }

    public void setLanguage(String language){
        keyGrabber = new RegexHandler();
        keyGrabber.addPatterns(language);
        keyGrabber.addPatterns("Syntax");
        generateMappings();
    }

    public Turtle addTurtle(int id) {
        System.out.println("adding");
        Turtle newTurtle = new Turtle(id, 0, 0, false, 0);
        turtleMap.put(id, newTurtle);
        return newTurtle;
    }

    public void setAddTurtleFunction(AddNewTurtleFunction function) { addTurtleFunction = function; }

    public Token getSymbolAsObj(String piece) throws SyntaxException{
        if (activeTurtles.size()<1) activeTurtles.add(turtleMap.get(0));
        String objectType = keyGrabber.getSymbol(piece);
        if (objectType.equals(VARIABLE_TYPE)) return getVariable(piece);
        if (objectType.equals(NEW_COMMAND_TYPE)) return getNewCommand(piece);
        if (objectType.equals(TO_TYPE)) return new To(piece, this::addNewCommand);
        Token token;
        try {
            Class c = mappings.get(objectType);
            Constructor objConstruct = c.getDeclaredConstructor(String.class);
            objConstruct.setAccessible(true);
            token = (Token) objConstruct.newInstance(piece);
            if (token instanceof DisplayCommand) ((DisplayCommand) token).setMyAction(setActionMap.get(objectType));
            if (token instanceof TurtleCommand) ((TurtleCommand) token).setActiveTurtles(activeTurtles);
            if (token instanceof QueryCommand) ((QueryCommand) token).setTurtle(activeTurtles.get(activeTurtles.size()-1));
            if (token instanceof MultiTurtleCommand) {
                ((MultiTurtleCommand) token).setAddNewTurtleFunction(addTurtleFunction);
                ((MultiTurtleCommand) token).setTurtleMap(turtleMap);
                ((MultiTurtleCommand) token).setActiveTurtles(activeTurtles);
            }
        } catch (Exception e) {
            throw new SyntaxException(e);
        }
        return token;
    }

    public ObservableList<String> getVariableList(){ return vars; }

    public ObservableList<String> getNewCommandList(){ return newCommands; }

    public void addAction(String key, DisplayAction action) { setActionMap.put(key, action); }

    private void generateMappings() {
        List<String> keys = keyGrabber.getKeys();
        for (String key: keys) {
            CodeType currentType = CodeType.valueOf(key.toUpperCase());
            mappings.put(key, currentType.getAssociatedClass());
        }
    }

    private void addNewCommand(Token token){
        NewCommand command = (NewCommand) token;
        newCommandMap.put(command.getName(), command);
        newCommands.add(command.getName());
    }

    private Token getVariable(String piece) {
        if (!variableMap.containsKey(piece)) {
            Variable variable = new Variable(piece);
            variableMap.put(piece, variable);
            vars.add(piece);
        }
        return variableMap.get(piece);
    }

    private Token getNewCommand(String piece) {
        if (newCommandMap.containsKey(piece)) return newCommandMap.get(piece);
        return new NewCommandName(piece);
    }
}

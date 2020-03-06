package slogo.model.tokens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.exceptions.LanguageFileNotFoundException;
import slogo.model.exceptions.SyntaxException;
import slogo.model.parse.RegexHandler;
import slogo.view.DisplayAction;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CodeFactory {

    private static final String VARIABLE_TYPE = "Variable";
    private static final String NEW_COMMAND_TYPE = "Command";
    private static final String TO_TYPE = "MakeUserInstruction";

    public static String PACKAGE_NAME = "slogo.model.tokens.";

    private RegexHandler keyGrabber;
    private Map<String, NewCommand> newCommandMap = new HashMap<>();
    private Map<String, Variable> variableMap = new HashMap<>();
    private ObservableList<Token> vars = FXCollections.observableArrayList();
    private ObservableList<Token> newCommands = FXCollections.observableArrayList();
    private Map<String, DisplayAction> setActionMap = new HashMap<>();

    public CodeFactory(String language) throws LanguageFileNotFoundException {
        setLanguage(language);
    }

    public void setLanguage(String language){
        keyGrabber = new RegexHandler();
        keyGrabber.addPatterns(language);
        keyGrabber.addPatterns("Syntax");
    }

    public Token getSymbolAsObj(String piece) throws SyntaxException{
        String objectType = keyGrabber.getSymbol(piece);
        if (objectType.equals(VARIABLE_TYPE)) return getVariable(piece);
        if (objectType.equals(NEW_COMMAND_TYPE)) return getNewCommand(piece);
        if (objectType.equals(TO_TYPE)) return new MakeUserInstruction(piece, this::addNewCommand);
        Token token;
        try {
            Class c = Class.forName(PACKAGE_NAME + objectType);
            Constructor objConstruct = c.getDeclaredConstructor(String.class);
            objConstruct.setAccessible(true);
            token = (Token) objConstruct.newInstance(piece);
            if (token instanceof DisplayCommand) ((DisplayCommand) token).setMyAction(setActionMap.get(objectType));
        } catch (Exception e) {
            throw new SyntaxException(e);
        }
        return token;
    }

    public ObservableList<Token> getVariableList(){ return vars; }

    public ObservableList<Token> getNewCommandList(){ return newCommands; }

    public void addAction(String key, DisplayAction action) { setActionMap.put(key, action); }

    private void addNewCommand(Token token){
        NewCommand command = (NewCommand) token;
        newCommandMap.put(command.getName(), command);
        newCommands.add(command);
    }

    public void updateVariableList(Token t){
        vars.remove(t);
        vars.add(t);
    }

    private Token getVariable(String piece) {
        if (!variableMap.containsKey(piece)) {
            Variable variable = new Variable(piece, this::updateVariableList);
            variableMap.put(piece, variable);
            vars.add(variable);
        }
        return variableMap.get(piece);
    }

    private Token getNewCommand(String piece) {
        if (newCommandMap.containsKey(piece)) return newCommandMap.get(piece);
        return new NewCommandName(piece);
    }
}

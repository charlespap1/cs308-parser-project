package slogo.model.parse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.tokens.NewCommandName;
import slogo.model.tokens.Token;
import slogo.model.tokens.Variable;
import slogo.model.exceptions.LanguageFileNotFoundException;
import slogo.model.exceptions.SyntaxException;
import slogo.model.tokens.instructions.*;
import slogo.model.tokens.instructions.display.DisplayCommand;
import slogo.model.tokens.instructions.misc.*;
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
        generateMappings();
    }

    public Token getSymbolAsObj(String piece) throws SyntaxException{
        String objectType = keyGrabber.getSymbol(piece);
        if (objectType.equals(VARIABLE_TYPE)) return getVariable(piece);
        if (objectType.equals(NEW_COMMAND_TYPE)) return getNewCommand(piece);
        if (objectType.equals(TO_TYPE)) return new MakeUserInstruction(piece, this::addNewCommand);
        Token token;
        try {
            Class c = mappings.get(objectType);
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

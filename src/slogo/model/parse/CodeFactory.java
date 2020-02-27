package slogo.model.parse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.code.NewCommandName;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.exceptions.LanguageFileNotFoundException;
import slogo.model.code.exceptions.SyntaxException;
import slogo.model.code.instructions.*;
import slogo.model.code.instructions.commands.ClearScreen;
import slogo.model.code.instructions.misc.To;
import slogo.view.ClearAction;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class CodeFactory {
    public static String VARIABLE_TYPE = "Variable";
    public static String NEW_COMMAND_TYPE = "Command";
    public static String TO_TYPE = "MakeUserInstruction";
    public static String CLEAR_TYPE = "ClearScreen";

    private RegexHandler keyGrabber;
    private Map<String, Class> mappings = new HashMap<>();
    private Map<String, NewCommand> newCommandMap = new HashMap<>();
    private Map<String, Variable> variableMap = new HashMap<>();
    private ObservableList<String> vars = FXCollections.observableArrayList();
    private ObservableList<String> newCommands = FXCollections.observableArrayList();
    private ClearAction clearAction;

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
        if (objectType.equals(TO_TYPE)) return new To(piece, this::addNewCommand);
        if (objectType.equals(CLEAR_TYPE)) return new ClearScreen(piece, clearAction);
        Token token;
        try {
            Class c = mappings.get(objectType);
            Constructor objConstruct = c.getDeclaredConstructor(String.class);
            objConstruct.setAccessible(true);
            token = (Token) objConstruct.newInstance(piece);
        } catch (Exception e) {
            throw new SyntaxException(e);
        }
        return token;
    }

    public ObservableList<String> getVariableList(){ return vars; }

    public ObservableList<String> getNewCommandList(){ return newCommands; }

    public void setClearAction(ClearAction action) { clearAction = action; }

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

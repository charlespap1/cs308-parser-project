package slogo.model.parse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.model.code.NewCommandName;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.instructions.*;
import slogo.model.code.instructions.misc.To;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class CodeFactory {
    private RegexHandler keyGrabber = new RegexHandler();
    private Map<String,Class> mappings = new HashMap<>();
    private Map<String, NewCommand> newCommandMap = new HashMap<>();
    private Map<String, Variable> variableMap = new HashMap<>();
    private ObservableList<String> vars = FXCollections.observableArrayList();
    private ObservableList<String> newCommands = FXCollections.observableArrayList();

    AddToListFunction addToNewCommandsList = this::addNewCommand;

    public CodeFactory(String language){
        keyGrabber.addPatterns(language);
        keyGrabber.addPatterns("Syntax");
        generateMappings();
    }

    private void addNewCommand(Token token){
        NewCommand command = (NewCommand) token;
        newCommandMap.put(command.getName(), command);
        newCommands.add(command.getName());
    }

    private void generateMappings() {
        List<String> keys = keyGrabber.getKeys();
        for(String key: keys){
            CodeType currentType = CodeType.valueOf(key.toUpperCase());
            mappings.put(key,currentType.getAssociatedClass());
        }
    }

    private Token getVariable(String piece){
        if (!variableMap.containsKey(piece)) {
            Variable variable = new Variable(piece);
            variableMap.put(piece, variable);
            vars.add(piece);
        }
        return variableMap.get(piece);
    }

    private Token getNewCommand(String piece){
        if (newCommandMap.containsKey(piece)) return newCommandMap.get(piece);
        return new NewCommandName(piece);
    }

    public Token getSymbolAsObj(String piece) {
        String objectType = keyGrabber.getSymbol(piece);
        if (objectType.equals("Variable")) return getVariable(piece);
        if (objectType.equals("Command")) return getNewCommand(piece);
        if (objectType.equals("MakeUserInstruction")) return new To(addToNewCommandsList);
        Token token = null;
        try{
            Class c = mappings.get(objectType);
            Constructor objConstruct = c.getDeclaredConstructor(String.class);
            objConstruct.setAccessible(true);
            token = (Token) objConstruct.newInstance(piece);
        }
        catch(Exception e){
            e.printStackTrace();
            //TODO: relay some message back to UI that code was no bueno.
        }
        return token;
    }

    public ObservableList<String> getVariableList(){ return vars; }
    public ObservableList<String> getNewCommandList(){ return newCommands; }
}

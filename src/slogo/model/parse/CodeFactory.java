package slogo.model.parse;

import slogo.model.code.NewCommandName;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.instructions.NewCommand;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeFactory {
    private RegexHandler keyGrabber;
    private Map<String,Class> mappings;
    private Map<String, NewCommand> newCommandMap;
    private Map<String, Variable> variableMap;

    public CodeFactory(String language){
        keyGrabber = new RegexHandler();
        mappings = new HashMap<>();
        newCommandMap = new HashMap<>();

        keyGrabber.addPatterns(language);

        // general checks, added last
        keyGrabber.addPatterns("Syntax");

        generateMappings();
    }

    private void generateMappings() {
        List<String> keys = keyGrabber.getKeys();
        System.out.println(keys);
    }

    private Token getVariable(String piece){
        if (!variableMap.containsKey(piece)) {
            Variable variable = new Variable(piece);
            variableMap.put(piece, variable);
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
        Token token = null;
        try{
            Class c = mappings.get(objectType);
            Constructor objConstruct = c.getDeclaredConstructor();
            objConstruct.setAccessible(true);
            token = (Token) objConstruct.newInstance();
            //token = (Token) objConstruct.newInstance(piece);
            // TODO: pass string
        }
        catch(Exception e){
            e.printStackTrace();
            //TODO: relay some message back to UI that code was no bueno.
        }
        return token;
    }
}

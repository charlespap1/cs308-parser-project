package slogo.model.parse;

import slogo.model.code.Token;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CodeFactory {
    private RegexHandler keyGrabber;
    private Map<String,Class> mappings;

    public CodeFactory(String language){
        keyGrabber = new RegexHandler();
        mappings = new HashMap<>();

        keyGrabber.addPatterns(language);

        // general checks, added last
        keyGrabber.addPatterns("Syntax");

        generateMappings();
    }

    private void generateMappings() {
        //TODO: add code to create mappings between Strings returned from parsing and class types
    }

    public Token getSymbolAsObj(String piece) {
        String objectType = keyGrabber.getSymbol(piece);
        Token ret = null;
        try{
            Class c = mappings.get(objectType);
            RegexHandler r = new RegexHandler();
            Constructor objConstruct = c.getDeclaredConstructor();
            objConstruct.setAccessible(true);
            ret = (Token)objConstruct.newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
            //TODO: relay some message back to UI that code was no bueno.
        }
        return ret;
    }
}

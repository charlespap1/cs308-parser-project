package slogo.model.parse;

import slogo.model.Code;
import slogo.model.Instruction;

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
    }

    public Code getSymbolAsObj(String piece) {
        String objectType = keyGrabber.getSymbol(piece);
        Code ret = null;
        try{
            Class c = mappings.get(objectType);
            RegexHandler r = new RegexHandler();
            Constructor objConstruct = c.getDeclaredConstructor();
            objConstruct.setAccessible(true);
            ret = (Code)objConstruct.newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
            //TODO: relay some message back to UI that code was no bueno.
        }
        return ret;
    }
}

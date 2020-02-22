package slogo.model.parse;

import java.util.HashMap;
import java.util.Map;

public class ClassSelector {
    private RegexHandler keyGrabber;
    private Map<String,Class> mappings;

    public ClassSelector(String language){
        keyGrabber = new RegexHandler();
        mappings = new HashMap<>();

        keyGrabber.addPatterns(language);

        // general checks, added last
        keyGrabber.addPatterns("Syntax");
    }
}

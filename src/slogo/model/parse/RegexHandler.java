package slogo.model.parse;

import java.util.*;
import java.util.regex.Pattern;

public class RegexHandler {
    public static final String RESOURCES_PACKAGE = "resources.commands.";
    // TODO: error handling
    public static final String ERROR = "NO MATCH";

    private List<Map.Entry<String, Pattern>> mySymbols = new ArrayList<>();

    /**
     * Adds the given resource file to this language's recognized types
     */
    public void addPatterns(String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
        for (String key : Collections.list(resources.getKeys())) {
            //TODO: this will never run into an error -- double check with backend people
            String regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    public String getSymbol(String text) {
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead
        return ERROR;
    }

    public List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        for(Map.Entry<String,Pattern> e: mySymbols)
            keys.add(e.getKey());
        return keys;
    }

    private boolean match(String text, Pattern regex) { return regex.matcher(text).matches(); }
}

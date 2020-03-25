package slogo.model.parse;

import slogo.model.exceptions.LanguageFileNotFoundException;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Class to get appropriate token key from an input string.
 */
public class RegexHandler {
    private static final String RESOURCES_PACKAGE = "resources.commands.";
    private static final String DEFAULT_FILE = "English";
    private static final String ERROR = "NO MATCH";

    private List<Map.Entry<String, Pattern>> mySymbols = new ArrayList<>();

    /**
     * Adds given resource file to language's recognized types
     * @param filename String name of file that is used to generate patterns
     * @throws LanguageFileNotFoundException exception if could not find the file or parse it to generate patterns
     */
    public void addPatterns(String filename) throws LanguageFileNotFoundException {
        try {
            ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + filename);
            loopThroughKeys(resources);
        } catch (Exception e) {
            ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + DEFAULT_FILE);
            loopThroughKeys(resources);
            throw new LanguageFileNotFoundException(e);
        }
    }

    /**
     *
     * @param text Text to be checked of type string
     * @return language's type associated with the given text if one exists
     */
    public String getSymbol(String text) {
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return ERROR;
    }

    private void loopThroughKeys(ResourceBundle resources) {
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
}

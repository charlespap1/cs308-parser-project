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

/**
 * Class to create Tokens from input string fragments and track new commands and variables that
 * have been create.
 * @author Charles, Michael, Natalie
 */
public class CodeFactory {

    private static final String VARIABLE_TYPE = "Variable";
    private static final String NEW_COMMAND_TYPE = "Command";
    private static final String TO_TYPE = "MakeUserInstruction";

    private static String PACKAGE_NAME = "slogo.model.tokens.";

    private RegexHandler keyGrabber;
    private Map<String, NewCommand> newCommandMap = new HashMap<>();
    private Map<String, Variable> variableMap = new HashMap<>();
    private ObservableList<Token> vars = FXCollections.observableArrayList();
    private ObservableList<Token> newCommands = FXCollections.observableArrayList();
    private Map<String, DisplayAction> setActionMap = new HashMap<>();

    /**
     * Constructs new CodeFactory.
     * @param language initial language to use for token recognition
     * @throws LanguageFileNotFoundException if language file does not exist
     */
    public CodeFactory(String language) throws LanguageFileNotFoundException {
        setLanguage(language);
    }


    /**
     * Uses RegexHandler to get token type of String, then creates new Token instance of appropriate type.
     * @param piece input String
     * @return new Token
     * @throws SyntaxException if String is improper syntax
     */
    public Token getSymbolAsObj(String piece) throws SyntaxException {
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

    /**
     * Gets observable list of variables that have been created to pass to front end.
     * @return variable list
     */
    public ObservableList<Token> getVariableList() {
        return vars;
    }

    /**
     * Gets observable list of new commands that have been created to pass to front end.
     * @return new command list
     */
    public ObservableList<Token> getNewCommandList() {
        return newCommands;
    }

    /**
     * Adds DisplayAction to map.
     * @param key command key corresponding to action
     * @param action DisplayAction from the front end corresponding to String key
     */
    public void addAction(String key, DisplayAction action) {
        setActionMap.put(key, action);
    }

    /**
     * Changes the language of the RegexHandler when user selects different language.
     * @param language new language to use
     */
    public void setLanguage(String language) {
        keyGrabber = new RegexHandler();
        keyGrabber.addPatterns(language);
        keyGrabber.addPatterns("Syntax");
    }

    /**
     * Function to save the current contents of the new commands list in a format that allows them to be read
     * back in as a file.
     * @return String representation of entire new commands list
     */
    public String saveNewCommands() {
        StringBuilder commandsAsString = new StringBuilder();
        for (Token newCommand : newCommands) {
            NewCommand command = (NewCommand) newCommand;
            commandsAsString.append(String.format("TO %s [ ", command.getName()));
            for (Token variable : command.getVariables()) {
                Variable var = (Variable) variable;
                commandsAsString.append(String.format("%s ", var.toString()));
            }
            commandsAsString.append("] [ ");
            for (Token instruction : command.getInstructions()) {
                Instruction instr = (Instruction) instruction;
                commandsAsString.append(String.format("%s ", instr.toString()));
            }
            commandsAsString.append("]\n");
        }
        return commandsAsString.toString();
    }

    /**
     * Function to save the current contents of the variables list in a format that allows them to be read back in
     * as a file.
     * @return String representation of entire variables list
     */
    public String saveVariables() {
        StringBuilder variablesAsString = new StringBuilder();
        for (Token variable : vars) {
            Variable var = (Variable) variable;
            variablesAsString.append(String.format("MAKE %s %f\n", var.toString(), var.getValue()));
        }
        return variablesAsString.toString();
    }

    /**
     * Used by Variable Tokens when their value changes so that variables list displayed  in the front end
     * reflects changes.
     * @param t Variable being changed
     */
    public void updateVariableList(Token t) {
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

    private void addNewCommand(Token token) {
        NewCommand command = (NewCommand) token;
        newCommandMap.put(command.getName(), command);
        newCommands.add(command);
    }
}

package slogo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import slogo.model.code.BracketClose;
import slogo.model.code.BracketOpen;
import slogo.model.code.ListSyntax;
import slogo.model.code.NewCommandName;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidCommandException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.exceptions.InvalidNumberArgumentsException;
import slogo.model.code.exceptions.LanguageFileNotFoundException;
import slogo.model.code.exceptions.CommandCannotDoListException;
import slogo.model.code.exceptions.SyntaxException;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.misc.To;
import slogo.model.parse.CodeFactory;
import slogo.model.parse.RegexHandler;
import slogo.view.ClearAction;

import java.io.File;
import java.util.*;

public class Model implements ModelAPI{

    enum SyntaxType{
        COMMENT,CONSTANT,VARIABLE,COMMAND,LISTSTART,LISTEND,GROUPSTART,GROUPEND,WHITESPACE,NEWLINE
    }

    // regular expression representing any whitespace characters (space, tab, or newline)
    public static final String WHITESPACE = "\\s+";
    public static final String SYNTAX = "Syntax";

    private Stack<Token> commands = new Stack<>();
    private CodeFactory createFromString;
    private Stack<Stack<Token>> arguments = new Stack<>();
    private RegexHandler typeCheck = new RegexHandler();
    private Turtle turtle;
    private StringProperty errorMessage = new SimpleStringProperty();

    public Model(StringProperty language) {
        typeCheck.addPatterns(SYNTAX);
        setupLanguage(language);
        turtle = new Turtle(0, 0, false, 0);
    }

    public void executeCode(String rawString) {
        errorMessage.set("");
        clearStacks();
        parseInstructions(rawString);
        if(!commands.isEmpty() || !arguments.isEmpty()){
            InvalidNumberArgumentsException e = new InvalidNumberArgumentsException();
            errorMessage.setValue(e.getMessage());
        }
        clearStacks();
    }

    public void executeCode(File f){
        //TODO: convert file f into rawString, then call parseInstructions with rawString
    }

    public Turtle getTurtle(){ return turtle; }

    public ObservableList<String> getVariableList(){ return createFromString.getVariableList(); }

    public ObservableList<String> getNewCommandsList(){ return createFromString.getNewCommandList(); }

    public StringProperty getErrorMessage(){ return errorMessage; }

    public void setClearAction(ClearAction action){ createFromString.setClearAction(action); }

    private void setupLanguage(StringProperty language) {
        try {
            createFromString = new CodeFactory(language.getValue());
            language.addListener((o, oldVal, newVal) ->  createFromString.setLanguage(newVal));
        }
        catch(LanguageFileNotFoundException e) {
            errorMessage.set(e.getMessage());
        }

    }

    private void parseInstructions(String rawString){
        try {
            String[] inputPieces = rawString.split(WHITESPACE);
            for (String piece: inputPieces) {
                if (piece.trim().length() > 0) {
                    addToAppropriateStack(piece);
                }
            }
        }
        catch (Exception e) {
            errorMessage.set(e.getMessage());
        }
    }

    private void addToAppropriateStack(String piece) throws InvalidCommandException, InvalidNumberArgumentsException {
        try {
            Token currItem = createFromString.getSymbolAsObj(piece);
            if (currItem instanceof NewCommandName && (commands.isEmpty() || !(commands.peek() instanceof To))) {
                throw new InvalidCommandException();
            } else if (currItem instanceof Instruction) {
                Instruction currInstr = (Instruction) currItem;
                if (currInstr.numRequiredArgs() == 0) {
                    if (commands.isEmpty()) {
                        try {
                            currInstr.execute(turtle);
                        } catch (Exception e) {
                            errorMessage.set(e.getMessage());
                        }
                    } else {
                        arguments.peek().push(currItem);
                        attemptToCreateFullInstruction();
                    }
                } else {
                    commands.push(currInstr);
                    arguments.push(new Stack<>());
                }
            } else {
                if (arguments.isEmpty() && commands.isEmpty()) {
                    throw new InvalidNumberArgumentsException();
                }
                arguments.peek().push(currItem);
                attemptToCreateFullInstruction();
            }
        }
        catch (SyntaxException e) {
            errorMessage.set(e.getMessage());
        }
    }

    private void attemptToCreateFullInstruction() {
        Instruction currCommand = (Instruction) commands.peek();
        if (enoughArgs(currCommand.numRequiredArgs())) {
            if (currCommand instanceof BracketOpen) {
                ListSyntax completeList = grabList(arguments.pop());
                arguments.peek().push(completeList);
                attemptToCreateFullInstruction();
            } else {
                Instruction currInstr = createCompleteInstruction(arguments.pop());
                if (commands.isEmpty()) {
                    try {
                        currInstr.execute(turtle);
                    }
                    catch(Exception e) {
                        errorMessage.set(e.getMessage());
                    }
                } else {
                    arguments.peek().push(currInstr);
                    attemptToCreateFullInstruction();
                }
            }
        }
    }

    private ListSyntax grabList(Stack<Token> args) {
        commands.pop();
        args.pop();
        List<Token> thingsInList = grabParameters(args);
        return new ListSyntax(thingsInList);
    }

    private Instruction createCompleteInstruction(Stack<Token> args) {
        Instruction currCommand = (Instruction) commands.pop();
        List<Token> params = grabParameters(args);
        currCommand.setParameters(params);
        return currCommand;
    }

    private List<Token> grabParameters(Stack<Token> args) {
        List<Token> params = new ArrayList<>();
        while (!args.isEmpty()) params.add(0,args.pop());
        return params;
    }

    private boolean enoughArgs(int numNeeded) {
        boolean isCompleteList = commands.peek() instanceof BracketOpen && arguments.peek().peek() instanceof BracketClose;
        boolean enoughCommandParameters = arguments.peek().size() >= numNeeded && numNeeded != -1;
        return isCompleteList || enoughCommandParameters;
    }

    private void clearStacks() {
        commands.clear();
        arguments.clear();
    }
}

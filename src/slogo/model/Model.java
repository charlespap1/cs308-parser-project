package slogo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import slogo.model.code.BracketClose;
import slogo.model.code.BracketOpen;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;
import slogo.model.parse.CodeFactory;
import slogo.model.parse.RegexHandler;

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
        parseInstructions(rawString);
    }

    public void executeCode(File f){
        //TODO: convert file f into rawString, then call parseInstructions with rawString
    }

    public Turtle getTurtle(){ return turtle; }

    public ObservableList<String> getVariableList(){ return createFromString.getVariableList(); }

    public ObservableList<String> getNewCommandsList(){ return createFromString.getNewCommandList(); }

    public StringProperty getErrorMessage(){ return errorMessage; }

    private void setupLanguage(StringProperty language) {
        createFromString = new CodeFactory(language.getValue());
        language.addListener((o, oldVal, newVal) ->  createFromString = new CodeFactory(newVal));
    }

    private void parseInstructions(String rawString) {
        try {
            List<String> inputPieces = Arrays.asList(rawString.split(WHITESPACE));
            for (String piece: inputPieces) {
                if (piece.trim().length() > 0) {
                    //TODO: error handling when no match found
                    addToAppropriateStack(piece);
                }
            }
        } catch (AssertionError e) {
            //TODO failure of parsing error
            System.out.println(e.getMessage());
        }
    }

    private void addToAppropriateStack(String piece) {
        // TODO: error handling if this line fails
        Token currItem = createFromString.getSymbolAsObj(piece);
        if(currItem instanceof Instruction) {
            Instruction currInstr = (Instruction) currItem;
            if (currInstr.numRequiredArgs() == 0){
                if (commands.isEmpty()) currInstr.execute(turtle);
                else {
                    arguments.peek().push(currItem);
                    attemptToCreateFullInstruction();
                }
            } else {
                commands.push(currInstr);
                arguments.push(new Stack<>());
            }
        } else {
            // TODO: this will be an error if there is an argument read in before any commands are read in
            // check if arg stack of stacks and command stack BOTH not empty, otherwise throw error
            arguments.peek().push(currItem);
            attemptToCreateFullInstruction();
        }
    }

    private void attemptToCreateFullInstruction() {
        //TODO: error handling null pointer if there is argument without command
        Instruction currCommand = (Instruction) commands.peek();
        int numRequiredArgs = currCommand.numRequiredArgs();
        if (enoughArgs(numRequiredArgs)) {
            if (commands.peek() instanceof BracketOpen) {
                ListSyntax completeList = grabList(arguments.pop());
                arguments.peek().push(completeList);
                attemptToCreateFullInstruction();
            } else {
                Instruction currInstr = createCompleteInstruction(arguments.pop());
                if (commands.isEmpty()) currInstr.execute(turtle);
                else {
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
}

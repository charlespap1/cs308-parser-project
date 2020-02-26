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
    private Stack<Token> arguments = new Stack<>();
    private CodeFactory createFromString;
    private RegexHandler typeCheck = new RegexHandler();
    private Turtle turtle;
    private StringProperty errorMessage = new SimpleStringProperty();

    public Model(String language) {
        typeCheck.addPatterns(SYNTAX);
        createFromString = new CodeFactory(language);
        turtle = new Turtle(0, 0, false, 0);
    }

    public void changeLanguage(String language) {
        createFromString = new CodeFactory(language);
    }

    public Turtle getTurtle(){ return turtle; }

    public void executeCode(String rawString) {
        parseInstructions(rawString);
    }

    public void executeCode(File f){
        //TODO: convert file f into rawString, then call parseInstructions with rawString
    }

    private void parseInstructions(String rawString) {
        try {
            List<String> inputPieces = Arrays.asList(rawString.split(WHITESPACE));
            for (String piece: inputPieces) {
                if (piece.trim().length() > 0) {
                    SyntaxType currType = SyntaxType.valueOf(typeCheck.getSymbol(piece).toUpperCase());
                    //TODO: error handling when no match found
                    addToAppropriateStack(piece);
                }
            }
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    private void addToAppropriateStack(String piece) {
        Token currentItem = createFromString.getSymbolAsObj(piece);
        if(currentItem instanceof Instruction) {
            Instruction currInstr = (Instruction) currentItem;
            if(currInstr.numRequiredArgs() == 0 && commands.isEmpty())
                currInstr.execute(turtle);
            else if(currInstr.numRequiredArgs() == 0){
                arguments.add(currentItem);
                attemptToCreateFullInstruction();
            }
            else
                commands.add(currInstr);
        }
        else if(currentItem instanceof BracketOpen){
            commands.add(currentItem);
            arguments.add(currentItem);
        }
        else {
            arguments.add(currentItem);
            attemptToCreateFullInstruction();
        }
    }

    private void listCreateFullHandler(){
        if(closeBracketExist()){
            commands.pop();
            ListSyntax newList = createCompleteList();
            arguments.push(newList);
            attemptToCreateFullInstruction();
        }
    }

    private ListSyntax createCompleteList() {
        List<Token> listContents = grabListContents();
        ListSyntax newList = new ListSyntax(listContents);
        return newList;
    }

    private List<Token> grabListContents() {
        List<Token> args = new ArrayList<>();
        //get rid of close bracket
        arguments.pop();
        //start with next in stack
        Token currToken = arguments.pop();
        while(!(currToken instanceof BracketOpen)){
            args.add(0,currToken);
            currToken = arguments.pop();
        }
        return args;
    }

    private boolean closeBracketExist() {
        return arguments.peek() instanceof BracketClose;
    }

    private void instrCreateFullHandler(){
        Instruction currCommand = (Instruction)commands.peek();
        int numRequiredArgs = currCommand.numRequiredArgs();
        if(enoughArgs(numRequiredArgs)){
            Instruction currInstr = createCompleteInstruction();
            if(currInstr != null){
                if(commands.isEmpty()){
                    currInstr.execute(turtle);
                }
                else{
                    arguments.push(currInstr);
                    attemptToCreateFullInstruction();
                }
            }
        }
    }

    private void attemptToCreateFullInstruction() {
        if(commands.peek() instanceof Instruction){
            instrCreateFullHandler();
        }
        else{
            listCreateFullHandler();
        }
    }

    private Instruction createCompleteInstruction() {
        Instruction currCommand = (Instruction)commands.pop();
        List<Token> params = grabParameters(currCommand.numRequiredArgs());
        if(params == null){
            commands.push(currCommand);
            return null;
        }
        else{
            currCommand.setParameters(params);
            return currCommand;
        }
    }

    private List<Token> grabParameters(int numArgsNeeded) {
        List<Token> params = new ArrayList<>();
        while(params.size() < numArgsNeeded){
            if(arguments.peek() instanceof BracketClose || arguments.peek() instanceof BracketOpen) {
                replenishArgsStack(params);
                return null;
            }
            else
                params.add(0,arguments.pop());
        }
        return params;
    }

    private void replenishArgsStack(List<Token> params) {
        while(params.size() > 0)
            arguments.push(params.remove(0));
    }

    private boolean enoughArgs(int numNeeded){
        return arguments.size() >= numNeeded;
    }

    public ObservableList<String> getVariableList(){ return createFromString.getVariableList(); }
    public ObservableList<String> getNewCommandsList(){ return createFromString.getNewCommandList(); }
    public StringProperty getErrorMessage(){ return errorMessage; }
}

package slogo.model;

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
    public static final String LANG = "English";
    public static final String SYNTAX = "Syntax";

    private Stack<Instruction> commands;
    private Stack<Token> arguments;
    private CodeFactory createFromString;
    private RegexHandler typeCheck;
    private Turtle turtle;

    public Model() {
        commands = new Stack<>();
        arguments = new Stack<>();
        createFromString = new CodeFactory(LANG);
        typeCheck = new RegexHandler();
        typeCheck.addPatterns(SYNTAX);
        turtle = new Turtle(0, 0, true, 0);
    }

    public Turtle getTurtle(){ return turtle; }

    public void executeCode(String rawString) {
        parseInstructions(rawString);
    }

    public void executeCode(File f){
        //TODO: convert file f into rawString, then call parseInstructions with rawString
    }

    private void parseInstructions(String rawString) {
        List<String> inputPieces = Arrays.asList(rawString.split(WHITESPACE));
        for (String piece: inputPieces) {
            if (piece.trim().length() > 0) {
                SyntaxType currType = SyntaxType.valueOf(typeCheck.getSymbol(piece).toUpperCase());
                //TODO: error handling when no match found
                addToAppropriateStack(currType, piece);
            }
        }
    }

    private void addToAppropriateStack(SyntaxType currType, String piece) {
        Token currentItem = createFromString.getSymbolAsObj(piece);
        if(currentItem instanceof Instruction)
            commands.add((Instruction) currentItem);
        else {
            //TODO: handle other types besides commands
            arguments.add(currentItem);
            attemptToCreateFullInstruction();
        }
    }

    private void attemptToCreateFullInstruction() {
        Instruction currCommand = commands.peek();
        int numRequiredArgs = currCommand.numRequiredArgs();
        if(enoughArgs(numRequiredArgs)){
            Instruction currInstr = createCompleteInstruction();
            if(commands.isEmpty()){
                currInstr.execute(turtle);
            }
            else{
                arguments.push(currInstr);
                attemptToCreateFullInstruction();
            }
        }
    }

    private Instruction createCompleteInstruction() {
        Instruction currCommand = commands.pop();
        List<Token> params = grabParameters(currCommand.numRequiredArgs());
        currCommand.setParameters(params);
        return currCommand;
    }

    private List<Token> grabParameters(int numArgsNeeded) {
        List<Token> params = new ArrayList<>();
        while(params.size() < numArgsNeeded){
            params.add(arguments.pop());
        }
        return params;
    }

    private boolean enoughArgs(int numNeeded){
        return arguments.size() >= numNeeded;
    }
}

package slogo.model.parse;

import slogo.model.code.Code;
import slogo.model.code.instructions.Instruction;

import java.util.*;

public class Parser {

    enum SyntaxType{
        COMMENT,CONSTANT,VARIABLE,COMMAND,LISTSTART,LISTEND,GROUPSTART,GROUPEND,WHITESPACE,NEWLINE
    }

    // regular expression representing any whitespace characters (space, tab, or newline)
    public static final String WHITESPACE = "\\s+";
    public static final String LANG = "English";
    public static final String SYNTAX = "Syntax";

    private Stack<Instruction> commands;
    private Stack<Code> arguments;
    private CodeFactory createFromString;
    private RegexHandler typeCheck;

    public Parser() {
        commands = new Stack<>();
        arguments = new Stack<>();
        createFromString = new CodeFactory(LANG);
        typeCheck = new RegexHandler();
        typeCheck.addPatterns(SYNTAX);
    }

    public void parseInstructions(String rawString) {
        List<String> inputPieces = Arrays.asList(rawString.split(WHITESPACE));
        for (String piece: inputPieces) {
            if (piece.trim().length() > 0) {
                SyntaxType currType = SyntaxType.valueOf(typeCheck.getSymbol(piece).toUpperCase());
                addToAppropriateStack(currType,piece);
            }
        }
    }

    private void addToAppropriateStack(SyntaxType currType, String piece) {
        if(currType == SyntaxType.COMMAND)
            commands.add((Instruction)createFromString.getSymbolAsObj(piece));
        else{
            arguments.add(createFromString.getSymbolAsObj(piece));
            attemptToCreateFullInstruction();
        }
    }

    private void attemptToCreateFullInstruction() {
        Instruction currCommand = commands.peek();
        int numRequiredArgs = currCommand.numRequiredArgs();
        if(enoughArgs(numRequiredArgs)){
            Instruction currInstr = createCompleteInstruction();
            if(commands.isEmpty()){
                //TODO: execute currInstr
            }
            else{
                arguments.push(currInstr);
                attemptToCreateFullInstruction();
            }
        }
    }

    private Instruction createCompleteInstruction() {
        Instruction currCommand = commands.pop();
        List<Code> params = grabParameters(currCommand.numRequiredArgs());
        currCommand.setParameters(params);
        return currCommand;
    }

    private List<Code> grabParameters(int numArgsNeeded) {
        List<Code> params = new ArrayList<>();
        while(params.size() < numArgsNeeded){
            Code currArg = arguments.pop();
            params.add(currArg);
        }
        return params;
    }

    private boolean enoughArgs(int numNeeded){
        return arguments.size() >= numNeeded;
    }
}

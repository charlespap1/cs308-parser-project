package slogo.model.parse;

import slogo.model.Instruction;
import slogo.model.Variable;

import java.util.*;

public class Parser {

    enum SyntaxType{
        COMMENT,CONSTANT,VARIABLE,COMMAND,LISTSTART,LISTEND,GROUPSTART,GROUPEND,WHITESPACE,NEWLINE
    }

    // regular expression representing any whitespace characters (space, tab, or newline)
    public static final String WHITESPACE = "\\s+";
    public static final String LANG = "English";

    private Stack<Object> commands;
    private Stack<Object> arguments;

    private InstructionFactory createFromString;

    private RegexHandler typeCheck;

    public Parser() {
        commands = new Stack<>();
        arguments = new Stack<>();
        createFromString = new InstructionFactory(LANG);
        typeCheck = new RegexHandler();
        typeCheck.addPatterns("Syntax");
    }

    public Map<String, Variable> parseVars(String rawString) {
        return null;
    }

    public void parseInstructions(String rawString) {
        List<String> inputPieces = Arrays.asList(rawString.split(WHITESPACE));
        for (String piece: inputPieces) {
            if (piece.trim().length() > 0) {
                SyntaxType currType = SyntaxType.valueOf(typeCheck.getSymbol(piece).toUpperCase());
                if(currType == SyntaxType.COMMAND)
                    commands.add(createFromString.getSymbolAsObj(piece));
                else{
                    arguments.add(createFromString.getSymbolAsObj(piece));
                    attemptToCreateFullInstruction();
                }
            }
        }
    }

    private void attemptToCreateFullInstruction() {

    }
}

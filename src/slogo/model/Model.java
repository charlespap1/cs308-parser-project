package slogo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import slogo.model.code.*;
import slogo.model.code.exceptions.InvalidCommandException;
import slogo.model.code.exceptions.InvalidNumberArgumentsException;
import slogo.model.code.exceptions.LanguageFileNotFoundException;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.misc.To;
import slogo.model.parse.CodeFactory;
import slogo.model.parse.RegexHandler;
import slogo.view.DisplayAction;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
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
    private static Map<Integer, Turtle> turtleMap = new HashMap<>();
    private StringProperty errorMessage = new SimpleStringProperty();
    private List<Turtle> activeTurtles = new ArrayList<>();
    private String currFullCommand = "";
    private boolean executed = false;

    private History history = new History();

    public Model(StringProperty language) {
        typeCheck.addPatterns(SYNTAX);
        setupLanguage(language);
        Turtle initialTurtle = new Turtle(1, 0, 0, false, 0);
        turtleMap.put(1, initialTurtle);
        activeTurtles.add(initialTurtle);
    }

    public void executeCode(String rawString) {
        errorMessage.set("");
        clearStacks();
        history.addNewProgram(new Program(generateStateMap(turtleMap)));
        parseInstructions(rawString);
        if(!commands.isEmpty() || !arguments.isEmpty()){
            InvalidNumberArgumentsException e = new InvalidNumberArgumentsException();
            errorMessage.setValue(e.getMessage());
        }
        clearStacks();
    }

    public void executeCode(File f){
        //TODO: convert file f into rawString, then call executeCode with rawString

    }

    public Map<Integer, State> generateStateMap(Map<Integer, Turtle> turtleMap) {
        Map<Integer, State> stateMap = new HashMap<>();
        for (int id : turtleMap.keySet()) {
            stateMap.put(id, new State(turtleMap.get(id)));
        }
        return stateMap;
    }

    public void undo() {
        try {
            Map<Integer, State> prevTurtleStates = history.undo();
            updateTurtlesWithStates(prevTurtleStates);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void redo() {
        try {
            Map<Integer, State> nextTurtleStates = history.redo();
            updateTurtlesWithStates(nextTurtleStates);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTurtlesWithStates(Map<Integer, State> turtleStates) {
        //update turtles that existed before undo/redo
        for (int id : turtleMap.keySet()) {
            if (!turtleStates.containsKey(id)) {
                // for undo, when a tell command was executed
                turtleMap.get(id).setVisible(false);
            } else {
                updateSingleTurtle(turtleMap.get(id), turtleStates.get(id));
            }
        }
    }

    private void updateSingleTurtle(Turtle turtle, State state) {
        turtle.setLocation(state.getxPos(), state.getyPos());
        turtle.setAngle(state.getAngle());
        turtle.setPenUp(state.getIsPenUp());
        turtle.setVisible(true);
    }

    public Turtle getTurtle(){ return turtleMap.get(1); }

    public ObservableList<String> getVariableList(){ return createFromString.getVariableList(); }

    public ObservableList<String> getNewCommandsList(){ return createFromString.getNewCommandList(); }

    public StringProperty getErrorMessage(){ return errorMessage; }

    private void setupLanguage(StringProperty language) {
        try {
            createFromString = new CodeFactory(language.getValue());
            language.addListener((o, oldVal, newVal) ->  createFromString.setLanguage(newVal));
        }
        catch(LanguageFileNotFoundException e) {
            errorMessage.set(e.getMessage());
        }

    }

    public static Turtle createOrGetTurtle(int id) {
        if (!turtleMap.containsKey(id)) {
            turtleMap.put(id, new Turtle(id, 0, 0, false, 0));
        }
        return turtleMap.get(id);
    }

    public List<Turtle> getActiveTurtles() {
        return activeTurtles;
    }

    public static Map<Integer, Turtle> getTurtleMap() {
        return turtleMap;
    }

    public void setAction(String key, DisplayAction action){
        createFromString.addAction(key, action);
    }

    private void parseInstructions(String rawString){
        try {
            String[] inputPieces = rawString.split(WHITESPACE);
            for (String piece: inputPieces) {
                if (piece.trim().length() > 0) {
                    addToAppropriateStack(piece);
                    currFullCommand += piece + " ";
                }
                if(executed){
//                    activeTurtles.get(0).setCurrCommand(currFullCommand);
////                    activeTurtles.get(0).setCurrCommand("");
                    history.getProgram(-1).addNewCommand(currFullCommand);
                    currFullCommand = "";
                    executed = false;
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
                addInstructionToStack(currInstr);
            } else {
                addArgumentToStack(currItem);
            }
        }
        catch (Exception e) {
            errorMessage.set(e.getMessage());
        }
    }

    private void addArgumentToStack(Token currItem) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (arguments.isEmpty() && commands.isEmpty()) {
            throw new InvalidNumberArgumentsException();
        }
        arguments.peek().push(currItem);
        attemptToCreateFullInstruction();
    }

    private void addInstructionToStack(Instruction currInstr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (currInstr.numRequiredArgs() == 0) {
            if (commands.isEmpty()) {
                currInstr.execute(activeTurtles);
            } else {
                arguments.peek().push(currInstr);
                attemptToCreateFullInstruction();
            }
        } else {
            commands.push(currInstr);
            arguments.push(new Stack<>());
        }
    }

    private void attemptToCreateFullInstruction() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Instruction currCommand = (Instruction) commands.peek();
        if (enoughArgs(currCommand.numRequiredArgs())) {
            if (currCommand instanceof BracketOpen) {
                ListSyntax completeList = grabList(arguments.pop());
                arguments.peek().push(completeList);
                attemptToCreateFullInstruction();
            } else {
                Instruction currInstr = createCompleteInstruction(arguments.pop());
                if (commands.isEmpty()) {
                    currInstr.execute(activeTurtles);
                    executed = true;
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

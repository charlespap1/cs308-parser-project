package slogo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import slogo.controller.AddNewTurtleFunction;
import slogo.model.code.*;
import slogo.model.code.exceptions.InvalidCommandException;
import slogo.model.code.exceptions.InvalidNumberArgumentsException;
import slogo.model.code.exceptions.LanguageFileNotFoundException;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.TurtleAction;
import slogo.model.code.instructions.misc.*;
import slogo.model.parse.CodeFactory;
import slogo.model.parse.RegexHandler;
import slogo.view.DisplayAction;

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
    private StringProperty errorMessage = new SimpleStringProperty();
    private String currFullCommand = "";
    private boolean executed = false;
    private TurtleMaster turtleMaster = new TurtleMaster();
    private Map<Double, Turtle> turtleMap = turtleMaster.getTurtleMap();
    private TurtleMasterAccessor accessor = new TurtleMasterAccessor() {
        @Override
        public double turtleCommandToMaster(TurtleAction action) { return turtleMaster.executeTurtleCommand(action); }
        @Override
        public double turtleQueryToMaster(TurtleAction action) { return turtleMaster.executeTurtleQuery(action); }
        @Override
        public double multiTurtleCommandToMaster(TurtleAction action, List<Double> turtles) { return turtleMaster.executeMultiTurtleCommand(action, turtles); }
    };

    private History history = new History();

    public Model(StringProperty language) {
        typeCheck.addPatterns(SYNTAX);
        setupLanguage(language);
        Turtle initialTurtle = new Turtle(0, 0, 0, false, 90);
        turtleMap.put(0.0, initialTurtle);
        //activeTurtles.add(initialTurtle);
        history.addNewProgram(new Program(generateStateMap(turtleMap)));
    }

    public void executeCode(String rawString) {
        errorMessage.set("");
        clearStacks();
        parseInstructions(rawString);
        // add next state?
        history.addNewProgram(new Program(generateStateMap(turtleMap)));
        history.setPointerToEnd();
        if(!commands.isEmpty() || !arguments.isEmpty()){
            InvalidNumberArgumentsException e = new InvalidNumberArgumentsException();
            errorMessage.setValue(e.getMessage());
        }
        clearStacks();
    }

    public void executeCode(File f){
        //TODO: convert file f into rawString, then call executeCode with rawString

    }

    public Map<Double, State> generateStateMap(Map<Double, Turtle> turtleMap) {
        Map<Double, State> stateMap = new HashMap<>();
        for (double id : turtleMap.keySet()) {
            stateMap.put(id, new State(turtleMap.get(id)));
        }
        return stateMap;
    }

    public void undo() {
        try {
            Map<Double, State> prevTurtleStates = history.undo();
            updateTurtlesWithStates(prevTurtleStates);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void redo() {
        try {
            Map<Double, State> nextTurtleStates = history.redo();
            updateTurtlesWithStates(nextTurtleStates);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTurtlesWithStates(Map<Double, State> turtleStates) {
        //update turtles that existed before undo/redo
        System.out.println(turtleMap);
        System.out.println(turtleStates);
        for (double id : turtleMap.keySet()) {
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

    public ObservableList<Token> getVariableList(){ return createFromString.getVariableList(); }

    public ObservableList<Token> getNewCommandsList(){ return createFromString.getNewCommandList(); }

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

    public void setAction(String key, DisplayAction action){
        createFromString.addAction(key, action);
    }

    public void setAddTurtleFunction(AddNewTurtleFunction function){ turtleMaster.setAddTurtleFunction(function); }

    public void setErrorMessage(String error) { errorMessage.setValue(error); }

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
                    history.getProgram(history.getProgramHistory().size() - 1).addNewCommand(currFullCommand);
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
            } else
            if (currItem instanceof Instruction) {
                Instruction currInstr = (Instruction) currItem;
                ((Instruction) currItem).setAccessor(accessor);
                addInstructionToStack(currInstr);
            } else {
                addArgumentToStack(currItem);
            }
        }
        catch (Exception e) {
            errorMessage.set(e.getMessage());
        }
    }

    private void addArgumentToStack(Token currItem) {
        if (arguments.isEmpty() && commands.isEmpty()) {
            throw new InvalidNumberArgumentsException();
        }
        arguments.peek().push(currItem);
        attemptToCreateFullInstruction();
    }

    private void addInstructionToStack(Instruction currInstr) {
        if (currInstr.numRequiredArgs() == 0) {
            if (commands.isEmpty()) {
                currInstr.execute();
                executed = true;
            } else {
                arguments.peek().push(currInstr);
                attemptToCreateFullInstruction();
            }
        } else {
            commands.push(currInstr);
            arguments.push(new Stack<>());
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
                    currInstr.execute();
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

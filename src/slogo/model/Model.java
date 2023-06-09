package slogo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import slogo.controller.AddNewTurtleFunction;
import slogo.model.exceptions.InvalidCommandException;
import slogo.model.exceptions.InvalidNumberArgumentsException;
import slogo.model.exceptions.LanguageFileNotFoundException;
import slogo.model.history.History;
import slogo.model.history.Program;
import slogo.model.history.State;
import slogo.model.parse.RegexHandler;
import slogo.model.tokens.*;
import slogo.view.DisplayAction;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Model for this project that implements the backend functionality and ModelAPI, handles all processing of code and creation of instructions to be executed.
 */
public class Model implements ModelAPI{
    public static final String WHITESPACE = "\\s+";
    public static final String SYNTAX = "Syntax";
    public static final String LINE_BREAK = "\\R+";

    private Stack<Token> commands = new Stack<>();
    private CodeFactory createFromString;
    private Stack<Stack<Token>> arguments = new Stack<>();
    private RegexHandler typeCheck = new RegexHandler();
    private StringProperty errorMessage = new SimpleStringProperty();
    private TurtleMaster turtleMaster = new TurtleMaster();
    private History history = new History();
    private TurtleMasterAccessor accessor = new TurtleMasterAccessor() {
        @Override
        public double turtleCommandToMaster(TurtleAction action) {
            return turtleMaster.executeTurtleCommand(action);
        }

        @Override
        public double turtleQueryToMaster(TurtleAction action) {
            return turtleMaster.executeTurtleQuery(action);
        }

        @Override
        public double multiTurtleCommandToMaster(TurtleAction action, List<Double> turtles) {
            return turtleMaster.executeMultiTurtleCommand(action, turtles);
        }
    };

    /**
     * Constructor to create backend model object.
     * @param language specifies what language the model handles upon creation.
     */
    public Model(StringProperty language) {
        typeCheck.addPatterns(SYNTAX);
        setupLanguage(language);
    }

    /**
     * Handles calling necessary methods for parsing and execution of code chunk provided
     * @param rawString String of code chunk to be executed
     */
    public void executeCode(String rawString) {
        errorMessage.set("");
        clearStacks();
        history.clearCurrentProgram();
        parseInstructions(rawString);
        history.addNewProgram(new Program(turtleMaster.generateStateMap()));
        if (!commands.isEmpty() || !arguments.isEmpty()) {
            InvalidNumberArgumentsException e = new InvalidNumberArgumentsException();
            errorMessage.setValue(e.getMessage());
        }
        clearStacks();
    }

    /**
     * Handles calling necessary methods for parsing and execution of code chunk provided
     * @param f File object of code to be parsed and run
     */
    public void executeCode(File f) {
        try {
            String path = f.getPath();
            String rawString = Files.readString(Paths.get(path));
            executeCode(rawString);
        } catch (Exception e) {
            errorMessage.setValue("Could Not Find File");
        }
    }

    /**
     * Handles calling necessary methods for parsing and execution of code chunk provided
     * @param instruction Instruction object that represents an instruction to be executed by backend
     */
    public void executeCode(Instruction instruction) {
        errorMessage.set("");
        clearStacks();
        history.clearCurrentProgram();
        instruction.execute();
        history.addCommand(instruction);
        history.addNewProgram(new Program(turtleMaster.generateStateMap()));
    }

    public ObservableList<Token> getVariableList() {
        return createFromString.getVariableList();
    }

    public ObservableList<Token> getHistoryList() {
        return history.getHistoryList();
    }

    public ObservableList<Token> getNewCommandsList() {
        return createFromString.getNewCommandList();
    }

    public String getNewVarsAndCommandsAsString() {
        return createFromString.saveNewCommands() + createFromString.saveVariables();
    }

    public BooleanProperty getUndoDisabled() {
        return history.getUndoDisabled();
    }

    public BooleanProperty getRedoDisabled() {
        return history.getRedoDisabled();
    }

    public StringProperty getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String error) {
        errorMessage.setValue(error);
    }

    public void setAction(String key, DisplayAction action) {
        createFromString.addAction(key, action);
    }

    private void setupLanguage(StringProperty language) {
        try {
            createFromString = new CodeFactory(language.getValue());
            language.addListener((o, oldVal, newVal) -> createFromString.setLanguage(newVal));
        } catch (LanguageFileNotFoundException e) {
            errorMessage.set(e.getMessage());
        }

    }

    public void setAddTurtleFunction(AddNewTurtleFunction function) {
        turtleMaster.setAddTurtleFunction(function);
        history.addNewProgram(new Program(turtleMaster.generateStateMap()));
    }

    public void clearHistory() {
        history.clearAll(new Program(turtleMaster.generateStateMap()));
    }

    public void undo() {
        try {
            Map<Double, State> prevTurtleStates = history.undo();
            turtleMaster.updateTurtlesWithStates(prevTurtleStates);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void redo() {
        try {
            Map<Double, State> nextTurtleStates = history.redo();
            turtleMaster.updateTurtlesWithStates(nextTurtleStates);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void parseInstructions(String rawString) {
        try {
            String[] lines = rawString.split(LINE_BREAK);
            for(String line : lines) {
                if (isNotComment(line)) {
                    String[] inputPieces = line.split(WHITESPACE);
                    for (String piece : inputPieces) {
                        if (piece.trim().length() > 0) {
                            addToAppropriateStack(piece);
                        }
                    }
                }
            }
        } catch (Exception e) {
            errorMessage.set(e.getMessage());
        }
    }

    private boolean isNotComment(String line) {
        return !(line.length() > 0 && line.substring(0,1).equals("#"));
    }

    private void addToAppropriateStack(String piece) throws InvalidCommandException, InvalidNumberArgumentsException {
        Token currItem = createFromString.getSymbolAsObj(piece);
        if (currItem instanceof NewCommandName) {
            checkIsEmpty();
            checkNotMakeUserInstruction();
            addArgumentToStack(currItem);
        } else if (currItem instanceof Instruction) {
            Instruction currInstr = (Instruction) currItem;
            ((Instruction) currItem).setAccessor(accessor);
            addInstructionToStack(currInstr);
        } else {
            addArgumentToStack(currItem);
        }
    }

    private void checkIsEmpty() {
        if(commands.isEmpty()) {
            throw new InvalidCommandException();
        }
    }

    private void checkNotMakeUserInstruction() {
        if(!commands.isEmpty()) {
            if (!(commands.peek() instanceof MakeUserInstruction)) {
                throw new InvalidCommandException();
            }
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
                history.addCommand(currInstr);
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
            if (currCommand instanceof ListStart) {
                ListSyntax completeList = grabList(arguments.pop());
                arguments.peek().push(completeList);
                attemptToCreateFullInstruction();
            } else {
                Instruction currInstr = createCompleteInstruction(arguments.pop());
                if (commands.isEmpty()) {
                    currInstr.execute();
                    history.addCommand(currInstr);
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
        while (!args.isEmpty()) params.add(0, args.pop());
        return params;
    }

    private boolean enoughArgs(int numNeeded) {
        boolean isCompleteList = commands.peek() instanceof ListStart && arguments.peek().peek() instanceof ListEnd;
        boolean enoughCommandParameters = arguments.peek().size() >= numNeeded && numNeeded != -1;
        return isCompleteList || enoughCommandParameters;
    }

    private void clearStacks() {
        commands.clear();
        arguments.clear();
    }
}

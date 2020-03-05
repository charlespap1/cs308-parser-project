package slogo.model.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program {

    private List<String> listOfCommands = new ArrayList<>();
    private Map<Integer, State> initialTurtleStates;

    public Program (Map<Integer, State> initialTurtleStates) {
        this.initialTurtleStates = initialTurtleStates;
    }

    public void addNewCommand(String command) {
        listOfCommands.add(command);
    }

    public Map<Integer, State> getInitialTurtleStates () {
        return initialTurtleStates;
    }

    public void execute() {
        //lambda.executeCode(listOfCommands);
    }
}

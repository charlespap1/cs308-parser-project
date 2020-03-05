package slogo.model.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program {

    private List<String> listOfCommands = new ArrayList<>();
    private Map<Double, State> initialTurtleStates;

    public Program (Map<Double, State> initialTurtleStates) {
        this.initialTurtleStates = initialTurtleStates;
    }

    public void addNewCommand(String command) {
        listOfCommands.add(command);
    }

    public Map<Double, State> getInitialTurtleStates () {
        return initialTurtleStates;
    }

    public void execute() {
        //lambda.executeCode(listOfCommands);
    }
}

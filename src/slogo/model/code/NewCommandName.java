package slogo.model.code;

public class NewCommandName implements Token {
    private String myName;

    public NewCommandName(String name){
        myName = name;
    }

    @Override
    public double execute() {
        return 0;
    }

    @Override
    public String toString() { return myName; }
}

package slogo.model.tokens;

public class BracketClose implements Token {
    public BracketClose(String name){
        super();
    }

    @Override
    public double execute() {
        return 0;
    }

    @Override
    public String toString() { return " ] "; }
}

package slogo.model.tokens;

public class ListEnd implements Token {
    public ListEnd(String name) {
        super();
    }

    @Override
    public double execute() {
        return 0;
    }

    @Override
    public String toString() {
        return " ] ";
    }
}

package slogo.model.tokens;

import java.util.List;

public class ListSyntax implements Token {
    private List<Token> contents;

    public ListSyntax(List<Token> stuff){
        contents = stuff;
    }

    public double execute(){
        return contents.size();
    }

    public List<Token> getContents() { return contents; }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for (Token t:contents) sb.append(" ").append(t.toString());
        sb.append(" ]");
        return sb.toString();
    }
}

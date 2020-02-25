package slogo.model.code;

import java.util.List;

public class ListSyntax implements Token {
    private List<Token> contents;

    public ListSyntax(List<Token> stuff){
        contents = stuff;
    }

    public int generateValue(){
        return contents.size();
    }

    public List<Token> getContents() { return contents; }

    public String toString(){
        return contents.toString();
    }
}

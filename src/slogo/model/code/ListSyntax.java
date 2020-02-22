package slogo.model.code;

import java.util.List;

public class ListSyntax implements Token {
    private List<Token> contents;

    public ListSyntax(List<Token> stuff){
        contents = stuff;
    }

    public int generateValue(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

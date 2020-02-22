package slogo.model;

import java.util.List;

public class ListSyntax implements Code {
    private List<Code> contents;

    public ListSyntax(List<Code> stuff){
        contents = stuff;
    }

    public int generateValue(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

package slogo.model.parse;

import slogo.model.code.Token;

@FunctionalInterface
public interface AddToListFunction {
    void addToList(Token token);
}

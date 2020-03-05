package slogo.model.parse;

import slogo.model.tokens.Token;

@FunctionalInterface
public interface AddToListFunction {
    void addToList(Token token);
}

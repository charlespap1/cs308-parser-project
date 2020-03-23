package slogo.model.parse;

import slogo.model.tokens.Token;

/**
 * Interface to allow new commands to be added to new commands list once they are built.
 * @author Natalie
 */
@FunctionalInterface
public interface AddToListFunction {
    void addToList(Token token);
}

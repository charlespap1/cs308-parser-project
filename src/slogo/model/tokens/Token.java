package slogo.model.tokens;

/**
 * Interface for all types of valid user input.
 */
public interface Token {
    double execute();

    String toString();
}
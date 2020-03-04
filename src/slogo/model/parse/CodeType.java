package slogo.model.parse;

//import slogo.model.code.BracketClose;
//import slogo.model.code.BracketOpen;
import slogo.model.code.Constant;
import slogo.model.code.Variable;
//import slogo.model.code.instructions.booleans.*;
import slogo.model.code.instructions.commands.*;
//import slogo.model.code.instructions.commands.ClearScreen;
//import slogo.model.code.instructions.math.*;
//import slogo.model.code.instructions.misc.*;
//import slogo.model.code.instructions.multipleturtles.*;
//import slogo.model.code.instructions.queries.*;

public enum CodeType {
    CLEARSCREEN(null),// ClearScreen.class),
    ISSHOWING(null),// TurtleShowingQuery.class),
    ASKWITH(null),// null),
    LEFT(null),// Left.class),
    OR(null),// Or.class),
    SETPOSITION(null),// SetXY.class),
    PRODUCT(null),// Product.class),
    SINE(null),// Sine.class),
    REPEAT(null),// Repeat.class),
    DIFFERENCE(null),// Difference.class),
    SETBACKGROUND(null),
    MAKEVARIABLE(null),// Make.class),
    LESSTHAN(null),// Less.class),
    ISPENDOWN(null),// PenDownQuery.class),
    RANDOM(null),// Random.class),
    GREATERTHAN(null),// Greater.class),
    EQUAL(null),// Equal.class),
    GETPENCOLOR(null),
    SETHEADING(null),// SetHeading.class),
    PI(null),// Pi.class),
    ID(null),
    YCOORDINATE(null),// YCor.class),
    NOTEQUAL(null),// NotEqual.class),
    FOR(null),// For.class),
    SETPALETTE(null),
    SUM(null),// Sum.class),
    ARCTANGENT(null),// Arctan.class),
    NOT(null),// Not.class),
    AND(null),// And.class),
    TURTLES(null),// Turtles.class),
    DOTIMES(null),// DoTimes.class),
    XCOORDINATE(null),// XCor.class),
    SETTOWARDS(null),// Towards.class),
    IF(null),// If.class),
    MINUS(null),// Minus.class),
    HIDETURTLE(null),// HideTurtle.class),
    COSINE(null),// Cosine.class),
    PENDOWN(null),// PenDown.class),
    SETPENSIZE(null),
    HEADING(null),// Heading.class),
    SETPENCOLOR(null),
    IFELSE(null),// IfElse.class),
    RIGHT(null),// Right.class),
    REMAINDER(null),// Remainder.class),
    BACKWARD(Back.class),
    ASK(null),
    NATURALLOG(null),// Log.class),
    HOME(null),// SetHome.class),
    PENUP(null),// PenUp.class),
    STAMP(null),
    TANGENT(null),// Tangent.class),
    MAKEUSERINSTRUCTION(null),// To.class),
    SETSHAPE(null),
    GETSHAPE(null),
    TELL(null),// Tell.class),
    FORWARD(Forward.class),
    SHOWTURTLE(null),// ShowTurtle.class),
    CLEARSTAMPS(null),
    QUOTIENT(null),// Quotient.class),
    POWER(null),// Pow.class),
    COMMENT(null),
    VARIABLE(Variable.class),
    GROUPEND(null),
    COMMAND(null),
    CONSTANT(Constant.class),
    LISTSTART(null),// BracketOpen.class),
    GROUPSTART(null),
    NEWLINE(null),
    LISTEND(null),// BracketClose.class),
    WHITESPACE(null),
    COMMONCOMMANDBUTTON(null);

    private Class associatedClass;

    CodeType(Class c){
        associatedClass = c;
    }

    public Class getAssociatedClass(){
        return associatedClass;
    }
}

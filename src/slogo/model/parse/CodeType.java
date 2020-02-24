package slogo.model.parse;

import slogo.model.code.Constant;
import slogo.model.code.Variable;
import slogo.model.code.instructions.commands.*;
import slogo.model.code.instructions.math.*;

public enum CodeType {
    CLEARSCREEN(null),
    ISSHOWING(null),
    ASKWITH(null),
    LEFT(Left.class),
    OR(null),
    SETPOSITION(SetXY.class),
    PRODUCT(Product.class),
    SINE(Sine.class),
    REPEAT(null),
    DIFFERENCE(null),
    SETBACKGROUND(null),
    MAKEVARIABLE(null),
    LESSTHAN(null),
    ISPENDOWN(null),
    RANDOM(Random.class),
    GREATERTHAN(null),
    EQUAL(null),
    GETPENCOLOR(null),
    SETHEADING(SetHeading.class),
    PI(Pi.class),
    ID(null),
    YCOORDINATE(null),
    NOTEQUAL(null),
    FOR(null),
    SETPALETTE(null),
    SUM(Sum.class),
    ARCTANGENT(Arctan.class),
    NOT(null),
    AND(null),
    TURTLES(null),
    DOTIMES(null),
    XCOORDINATE(null),
    SETTOWARDS(Towards.class),
    IF(null),
    MINUS(Minus.class),
    HIDETURTLE(HideTurtle.class),
    COSINE(Cosine.class),
    PENDOWN(PenDown.class),
    SETPENSIZE(null),
    HEADING(null),
    SETPENCOLOR(null),
    IFELSE(null),
    RIGHT(Right.class),
    REMAINDER(Remainder.class),
    BACKWARD(Back.class),
    ASK(null),
    NATURALLOG(Log.class),
    HOME(SetHome.class),
    PENUP(PenUp.class),
    STAMP(null),
    TANGENT(Tangent.class),
    MAKEUSERINSTRUCTION(null),
    SETSHAPE(null),
    GETSHAPE(null),
    TELL(null),
    FORWARD(Forward.class),
    SHOWTURTLE(ShowTurtle.class),
    CLEARSTAMPS(null),
    QUOTIENT(Quotient.class),
    POWER(Pow.class),
    COMMENT(null),
    VARIABLE(Variable.class),
    GROUPEND(null),
    COMMAND(null),
    CONSTANT(Constant.class),
    LISTSTART(null),
    GROUPSTART(null),
    NEWLINE(null),
    LISTEND(null),
    WHITESPACE(null);

    private Class associatedClass;
    CodeType(Class c){
        associatedClass = c;
    }

    Class getAssociatedClass(){
        return associatedClass;
    }
}

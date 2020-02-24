package slogo.model.parse;

import slogo.model.code.Constant;
import slogo.model.code.Variable;
import slogo.model.code.instructions.*;

public enum CodeType {
    CLEARSCREEN(null),
    ISSHOWING(null),
    ASKWITH(null),
    LEFT(Left.class),
    OR(null),
    SETPOSITION(null),
    PRODUCT(null),
    SINE(null),
    REPEAT(null),
    DIFFERENCE(null),
    SETBACKGROUND(null),
    MAKEVARIABLE(null),
    LESSTHAN(null),
    ISPENDOWN(null),
    RANDOM(null),
    GREATERTHAN(null),
    EQUAL(null),
    GETPENCOLOR(null),
    SETHEADING(SetHeading.class),
    PI(null),
    ID(null),
    YCOORDINATE(null),
    NOTEQUAL(null),
    FOR(null),
    SETPALETTE(null),
    SUM(null),
    ARCTANGENT(null),
    NOT(null),
    AND(null),
    TURTLES(null),
    DOTIMES(null),
    XCOORDINATE(null),
    SETTOWARDS(null),
    IF(null),
    MINUS(null),
    HIDETURTLE(null),
    COSINE(null),
    PENDOWN(PenDown.class),
    SETPENSIZE(null),
    HEADING(null),
    SETPENCOLOR(null),
    IFELSE(null),
    RIGHT(Right.class),
    REMAINDER(null),
    BACKWARD(Back.class),
    ASK(null),
    NATURALLOG(null),
    HOME(SetHome.class),
    PENUP(PenUp.class),
    STAMP(null),
    TANGENT(null),
    MAKEUSERINSTRUCTION(null),
    SETSHAPE(null),
    GETSHAPE(null),
    TELL(null),
    FORWARD(Forward.class),
    SHOWTURTLE(null),
    CLEARSTAMPS(null),
    QUOTIENT(null),
    POWER(null),
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

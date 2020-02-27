package slogo.model.parse;

import slogo.model.code.BracketClose;
import slogo.model.code.BracketOpen;
import slogo.model.code.Constant;
import slogo.model.code.Variable;
import slogo.model.code.instructions.booleans.*;
import slogo.model.code.instructions.commands.*;
import slogo.model.code.instructions.math.*;
import slogo.model.code.instructions.misc.*;
import slogo.model.code.instructions.queries.*;

public enum CodeType {
    CLEARSCREEN(ClearScreen.class),
    ISSHOWING(TurtleShowingQuery.class),
    ASKWITH(null),
    LEFT(Left.class),
    OR(Or.class),
    SETPOSITION(SetXY.class),
    PRODUCT(Product.class),
    SINE(Sine.class),
    REPEAT(Repeat.class),
    DIFFERENCE(Difference.class),
    SETBACKGROUND(null),
    MAKEVARIABLE(Make.class),
    LESSTHAN(Less.class),
    ISPENDOWN(PenDownQuery.class),
    RANDOM(Random.class),
    GREATERTHAN(Greater.class),
    EQUAL(Equal.class),
    GETPENCOLOR(null),
    SETHEADING(SetHeading.class),
    PI(Pi.class),
    ID(null),
    YCOORDINATE(YCor.class),
    NOTEQUAL(NotEqual.class),
    FOR(For.class),
    SETPALETTE(null),
    SUM(Sum.class),
    ARCTANGENT(Arctan.class),
    NOT(Not.class),
    AND(And.class),
    TURTLES(null),
    DOTIMES(DoTimes.class),
    XCOORDINATE(XCor.class),
    SETTOWARDS(Towards.class),
    IF(If.class),
    MINUS(Minus.class),
    HIDETURTLE(HideTurtle.class),
    COSINE(Cosine.class),
    PENDOWN(PenDown.class),
    SETPENSIZE(null),
    HEADING(Heading.class),
    SETPENCOLOR(null),
    IFELSE(IfElse.class),
    RIGHT(Right.class),
    REMAINDER(Remainder.class),
    BACKWARD(Back.class),
    ASK(null),
    NATURALLOG(Log.class),
    HOME(SetHome.class),
    PENUP(PenUp.class),
    STAMP(null),
    TANGENT(Tangent.class),
    MAKEUSERINSTRUCTION(To.class),
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
    LISTSTART(BracketOpen.class),
    GROUPSTART(null),
    NEWLINE(null),
    LISTEND(BracketClose.class),
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

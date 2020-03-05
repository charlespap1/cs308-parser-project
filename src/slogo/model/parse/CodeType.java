package slogo.model.parse;

import slogo.model.code.BracketClose;
import slogo.model.code.BracketOpen;
import slogo.model.code.Constant;
import slogo.model.code.Variable;
import slogo.model.code.instructions.booleans.*;
import slogo.model.code.instructions.commands.*;
import slogo.model.code.instructions.display.*;
import slogo.model.code.instructions.math.*;
import slogo.model.code.instructions.misc.*;
import slogo.model.code.instructions.multipleturtles.*;
import slogo.model.code.instructions.queries.*;

public enum CodeType {
    CLEARSCREEN(ClearScreen.class),
    ISSHOWING(TurtleShowingQuery.class),
    ASKWITH(AskWith.class),
    LEFT(Left.class),
    OR(Or.class),
    SETPOSITION(SetXY.class),
    PRODUCT(Product.class),
    SINE(Sine.class),
    REPEAT(Repeat.class),
    DIFFERENCE(Difference.class),
    SETBACKGROUND(SetBackground.class),
    MAKEVARIABLE(Make.class),
    LESSTHAN(Less.class),
    ISPENDOWN(PenDownQuery.class),
    RANDOM(Random.class),
    GREATERTHAN(Greater.class),
    EQUAL(Equal.class),
    GETPENCOLOR(PenColor.class),
    SETHEADING(SetHeading.class),
    PI(Pi.class),
    ID(Id.class),
    YCOORDINATE(YCor.class),
    NOTEQUAL(NotEqual.class),
    FOR(For.class),
    SETPALETTE(SetPalette.class),
    SUM(Sum.class),
    ARCTANGENT(Arctan.class),
    NOT(Not.class),
    AND(And.class),
    TURTLES(Turtles.class),
    DOTIMES(DoTimes.class),
    XCOORDINATE(XCor.class),
    SETTOWARDS(Towards.class),
    IF(If.class),
    MINUS(Minus.class),
    HIDETURTLE(HideTurtle.class),
    COSINE(Cosine.class),
    PENDOWN(PenDown.class),
    SETPENSIZE(SetPenSize.class),
    HEADING(Heading.class),
    SETPENCOLOR(SetPenColor.class),
    IFELSE(IfElse.class),
    RIGHT(Right.class),
    REMAINDER(Remainder.class),
    BACKWARD(Back.class),
    ASK(Ask.class),
    NATURALLOG(Log.class),
    HOME(SetHome.class),
    PENUP(PenUp.class),
    STAMP(null), //stamp
    TANGENT(Tangent.class),
    MAKEUSERINSTRUCTION(To.class),
    SETSHAPE(SetShape.class),
    GETSHAPE(Shape.class),
    TELL(Tell.class),
    FORWARD(Forward.class),
    SHOWTURTLE(ShowTurtle.class),
    CLEARSTAMPS(null), //stamps?
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
    WHITESPACE(null);

    private Class associatedClass;

    CodeType(Class c){
        associatedClass = c;
    }

    public Class getAssociatedClass(){
        return associatedClass;
    }
}

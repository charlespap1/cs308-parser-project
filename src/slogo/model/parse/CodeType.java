package slogo.model.parse;

import slogo.model.tokens.ListEnd;
import slogo.model.tokens.ListStart;
import slogo.model.tokens.Constant;
import slogo.model.tokens.Variable;
import slogo.model.tokens.instructions.booleans.*;
import slogo.model.tokens.instructions.commands.*;
import slogo.model.tokens.instructions.display.*;
import slogo.model.tokens.instructions.math.*;
import slogo.model.tokens.instructions.misc.*;
import slogo.model.tokens.instructions.multipleturtles.*;
import slogo.model.tokens.instructions.queries.*;

public enum CodeType {
    CLEARSCREEN(ClearScreen.class),
    ISSHOWING(IsShowing.class),
    ASKWITH(AskWith.class),
    LEFT(Left.class),
    OR(Or.class),
    SETPOSITION(SetPosition.class),
    PRODUCT(Product.class),
    SINE(Sine.class),
    REPEAT(Repeat.class),
    DIFFERENCE(Difference.class),
    SETBACKGROUND(SetBackground.class),
    MAKEVARIABLE(MakeVariable.class),
    LESSTHAN(LessThan.class),
    ISPENDOWN(IsPenDown.class),
    RANDOM(Random.class),
    GREATERTHAN(GreaterThan.class),
    EQUAL(Equal.class),
    GETPENCOLOR(GetPenColor.class),
    SETHEADING(SetHeading.class),
    PI(Pi.class),
    ID(Id.class),
    YCOORDINATE(YCoordinate.class),
    NOTEQUAL(NotEqual.class),
    FOR(For.class),
    SETPALETTE(SetPalette.class),
    SUM(Sum.class),
    ARCTANGENT(ArcTangent.class),
    NOT(Not.class),
    AND(And.class),
    TURTLES(Turtles.class),
    DOTIMES(DoTimes.class),
    XCOORDINATE(XCoordinate.class),
    SETTOWARDS(SetTowards.class),
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
    BACKWARD(Backward.class),
    ASK(Ask.class),
    NATURALLOG(NaturalLog.class),
    HOME(Home.class),
    PENUP(PenUp.class),
    STAMP(null), //stamp
    TANGENT(Tangent.class),
    MAKEUSERINSTRUCTION(MakeUserInstruction.class),
    SETSHAPE(SetShape.class),
    GETSHAPE(GetShape.class),
    TELL(Tell.class),
    FORWARD(Forward.class),
    SHOWTURTLE(ShowTurtle.class),
    CLEARSTAMPS(null), //stamps?
    QUOTIENT(Quotient.class),
    POWER(Power.class),
    COMMENT(null),
    VARIABLE(Variable.class),
    GROUPEND(null),
    COMMAND(null),
    CONSTANT(Constant.class),
    LISTSTART(ListStart.class),
    GROUPSTART(null),
    NEWLINE(null),
    LISTEND(ListEnd.class),
    WHITESPACE(null);

    private Class associatedClass;

    CodeType(Class c){
        associatedClass = c;
    }

    public Class getAssociatedClass(){
        return associatedClass;
    }
}

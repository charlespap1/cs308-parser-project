package slogo.model.parse;

import slogo.model.code.NewCommandName;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.instructions.*;

import java.lang.reflect.Constructor;
import java.util.*;


//[ClearScreen, IsShowing, AskWith, Left, Or, SetPosition, Product, Sine, Repeat, Difference, SetBackground, MakeVariable, LessThan, IsPenDown, Random, GreaterThan, Equal, GetPenColor, SetHeading, Pi, ID, YCoordinate, NotEqual, For, SetPalette, Sum, ArcTangent, Not, And, Turtles, DoTimes, XCoordinate, SetTowards, If, Minus, HideTurtle, Cosine, PenDown, SetPenSize, Heading, SetPenColor, IfElse, Right, Remainder, Backward, Ask, NaturalLog, Home, PenUp, Stamp, Tangent, MakeUserInstruction, SetShape, GetShape, Tell, Forward, ShowTurtle, ClearStamps, Quotient, Power, Comment, Variable, GroupEnd, Command, Constant, ListStart, GroupStart, Newline, ListEnd, Whitespace]


public class CodeFactory {
    private RegexHandler keyGrabber;
    private Map<String,Class> mappings;
    private Map<String, NewCommand> newCommandMap;
    private Map<String, Variable> variableMap;

    public CodeFactory(String language){
        keyGrabber = new RegexHandler();
        mappings = new HashMap<>();
        newCommandMap = new HashMap<>();

        keyGrabber.addPatterns(language);

        // general checks, added last
        keyGrabber.addPatterns("Syntax");

        generateMappings();
    }

    private void generateMappings() {
        List<String> keys = keyGrabber.getKeys();
        for(String key: keys){
            CodeType currentType = CodeType.valueOf(key.toUpperCase());
            mappings.put(key,currentType.getAssociatedClass());
        }
    }

    private Token getVariable(String piece){
        if (!variableMap.containsKey(piece)) {
            Variable variable = new Variable(piece);
            variableMap.put(piece, variable);
        }
        return variableMap.get(piece);
    }

    private Token getNewCommand(String piece){
        if (newCommandMap.containsKey(piece)) return newCommandMap.get(piece);
        return new NewCommandName(piece);
    }

    public Token getSymbolAsObj(String piece) {
        String objectType = keyGrabber.getSymbol(piece);
        if (objectType.equals("Variable")) return getVariable(piece);
        if (objectType.equals("Command")) return getNewCommand(piece);
        Token token = null;
        try{
            Class c = mappings.get(objectType);
            Constructor objConstruct = c.getDeclaredConstructor(String.class);
            objConstruct.setAccessible(true);
            token = (Token) objConstruct.newInstance(piece);
        }
        catch(Exception e){
            e.printStackTrace();
            //TODO: relay some message back to UI that code was no bueno.
        }
        return token;
    }
}

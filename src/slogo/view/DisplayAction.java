package slogo.view;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@FunctionalInterface
public interface DisplayAction {
    int execute(List<Double> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}

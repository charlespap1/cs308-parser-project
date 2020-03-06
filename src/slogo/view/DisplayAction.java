package slogo.view;
import java.util.List;

@FunctionalInterface
public interface DisplayAction {
    int execute(List<Double> params);
}

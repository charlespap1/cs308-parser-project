package slogo.view;
import java.util.List;

/**
 * @author Natalie
 */
@FunctionalInterface
public interface DisplayAction {
    int execute(List<Double> params);
}

package slogo.view;

import javafx.scene.paint.Color;
import slogo.State;

public interface View {

    String getInstruction() throws NullPointerException;
    void showError(String errorMessage);
    void changeCanvasColor(Color color);
}

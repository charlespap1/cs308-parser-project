package slogo.view;

import javafx.scene.paint.Color;
import slogo.State;

public interface View {

    String getInstruction() throws NullPointerException;
    void changeCanvasColor(Color color);
}

package slogo.model;

import java.io.File;

public interface ModelAPI {

    void executeCode(String rawCode);

    void executeCode(File f);

    Turtle getTurtle();
}

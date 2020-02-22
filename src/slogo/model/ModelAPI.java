package slogo.model;

import slogo.State;

import java.io.File;
import java.util.List;

public interface ModelAPI {

    public abstract void executeCode(String rawCode);

    public abstract void executeCode(File f);

}

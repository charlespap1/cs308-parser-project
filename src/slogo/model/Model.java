package slogo.model;

import java.util.List;

public interface Model {

    List<Instruction> executeCode(String rawCode);
}

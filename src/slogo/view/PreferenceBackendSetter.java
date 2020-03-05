package slogo.view;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;


public class PreferenceBackendSetter {

  private List<Turtle> myTurtles;
  private DrawingCanvas myDrawingCanvas;
  private Group myRoot;

  public PreferenceBackendSetter(List<Turtle> allTurtles, DrawingCanvas dc, Group root)
  {
    myTurtles = allTurtles;
    myDrawingCanvas = dc;
    myRoot = root;
  }

  public int setPenColor(List<Double> params) {
    int index = params.get(0).intValue();
    //TODO: implement with palette

    //for (Turtle turtle : myTurtles) turtle.changePenColor(Color.RED);
    return index;
  }

  public int setBackground(List<Double> params){
    int index = params.get(0).intValue();
    //TODO: implement with palette
    myDrawingCanvas.changeBackground(Color.RED);
    return index;
  }

  public int setPenThickness(List<Double> params){
    int index = params.get(0).intValue();
    //TODO: implement with palette ? unsure
    for (Turtle turtle : myTurtles) turtle.setThickness(index);
    return index;
  }

  public int setTurtleImage(List<Double> params){
    int index = params.get(0).intValue();
    //TODO: implement with palette
    for (Turtle turtle : myTurtles) turtle.changeImage(null);
    return index;
  }

  public int setPalette(List<Double> params){return 0;}

  public int getPenColor(List<Double> params) { return 0;}
  public int getShape(List<Double> params) { return 0; }

  public int clearScreen(List<Double> params) {
    for(Turtle t: myTurtles)
    {
      t.returnTurtleToDefault();
    }

    // use line manager to clear lines instead.
    // myRoot.getChildren().removeAll(myDrawingCanvas.getLines());
    return 0;
  }

}

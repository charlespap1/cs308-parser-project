package slogo.view.selectors;

import javafx.scene.paint.Color;

public class RGBHelper {

  public Color getColor(String rgb)
  {
    String [] rgbVals = rgb.split(",");
    int r = Integer.parseInt(rgbVals[0]);
    int g = Integer.parseInt(rgbVals[1]);
    int b = Integer.parseInt(rgbVals[2]);

    return Color.rgb(r,g,b);
  }


}

package slogo.view;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public interface StaticViewElement {

  /**
   * Allows the setup to get the node of the element to add it to the root
   * @return
   */
  Node getView();

  /**
   * Allows for the language of the text on element to by dynamically binded
   * and change with the chosen language
   * @param sp
   */
  void setTitleProperty(StringProperty sp);
}
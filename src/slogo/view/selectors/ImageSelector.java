package slogo.view.selectors;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.ResourceBundle;

/**
 * An abstract class for selecting an image from a list of images in the GUI. Based heavily on ColorSelector.
 */
public abstract class ImageSelector {
    public static final int HBOX_SPACING = 10;
    public static final double IMAGE_SELECTOR_HEIGHT = 17;
    public static final String DEFAULT_IMAGE_SETTER = "-fx-background-color: ";

    private HBox myHolder;
    private List<String> myImages;
    private ResourceBundle myResources;

    public ImageSelector(String intro, double x, double y, List<String> images, String resourcePackage)
    {
        myHolder = new HBox(HBOX_SPACING);
        Text title = new Text(intro);
        myHolder.getChildren().add(title);
        myHolder.setLayoutX(x);
        myHolder.setLayoutY(y);

        myResources = ResourceBundle.getBundle(resourcePackage);

        myImages = images;
        setButtons();
    }

    private void setButtons()
    {
        for(String currImage: myImages){
            Button newColor = new Button();
            newColor.setMaxHeight(IMAGE_SELECTOR_HEIGHT);
            newColor.setMinHeight(IMAGE_SELECTOR_HEIGHT);
            String hex = myResources.getString(currImage);
            newColor.setStyle(DEFAULT_IMAGE_SETTER + hex);

            newColor.setOnAction(e -> changeSomething(hex));

            myHolder.getChildren().add(newColor);
        }
    }

    public abstract void changeSomething(String hex);

    public Node getView()
    {
        return myHolder;
    }
}

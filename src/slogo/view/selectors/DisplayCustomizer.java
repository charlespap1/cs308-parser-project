package slogo.view.selectors;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import slogo.view.DisplayAction;
import slogo.view.StaticViewElement;

import java.util.*;

/**
 * Within this class we consolidated all of our Color, Background, Pen and
 * Turtle face selectors. This class allows for dynamic pallets for each of
 * these view elements
 *
 * @author Juliet, Natalie
 */
public class DisplayCustomizer implements StaticViewElement {

    private static final int BOX_SPACING = 5;
    private static final double COLOR_SELECTOR_HEIGHT = 17;
    private static final String DEFAULT_BACKGROUND_SETTER = "-fx-background-color: rgb(%s)";
    private static final String COLOR_PATH = "resources.colors.BackgroundColors";
    private static final String IMAGE_PATH = "resources.colors.TurtleImages";
    private static final int DEFAULT_PEN_COLOR = 0;
    private static final int DEFAULT_TURTLE_FACE = 0;
    private static final int DEFAULT_BACKGROUND_COLOR = 7;

    private static final ResourceBundle myImageResource = ResourceBundle.getBundle(IMAGE_PATH);
    private static final ResourceBundle myColorResource = ResourceBundle.getBundle(COLOR_PATH);

    private List<Button> backgroundButtons;
    private List<Button> penButtons;
    private List<Button> imageButtons;

    protected VBox myHolder = new VBox(BOX_SPACING);
    protected HBox myBackgroundHolder = new HBox(BOX_SPACING);
    protected HBox myPenHolder = new HBox(BOX_SPACING);
    protected HBox myCharacterHolder = new HBox(BOX_SPACING);

    protected List<String> colors = new ArrayList<>();
    protected List<String> turtleFaces = new ArrayList<>();

    private List<String> colorKeys;
    private List<String> imageKeys;

    private int penColorIndex = DEFAULT_PEN_COLOR;
    private int imageIndex = DEFAULT_TURTLE_FACE;
    private int backgroundColorIndex = DEFAULT_BACKGROUND_COLOR;

    public DisplayCustomizer(double x, double y) {
        myHolder.setLayoutX(x);
        myHolder.setLayoutY(y);
        myHolder.setAlignment(Pos.CENTER);

        colorKeys = Collections.list(myColorResource.getKeys());
        imageKeys = Collections.list(myImageResource.getKeys());

        myHolder.getChildren().addAll(myBackgroundHolder, myCharacterHolder, myPenHolder);

        backgroundButtons = createButtons(colorKeys, myBackgroundHolder);
        penButtons = createButtons(colorKeys, myPenHolder);
        imageButtons = createButtons(imageKeys, myCharacterHolder);
        buildLists();

        myBackgroundHolder.setAlignment(Pos.CENTER_RIGHT);
        myCharacterHolder.setAlignment(Pos.CENTER_RIGHT);
        myPenHolder.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Getter methods so we are able to grab indexes to communicate with backend
     *
     * @return
     */
    public int getPenIndex() {
        return penColorIndex;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public int getBackgroundIndex() {
        return backgroundColorIndex;
    }

    public Color getColor(int index) {
        return getColor(colors.get(index));
    }

    public Image getImage(int index) {
        imageIndex = index;
        return new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(turtleFaces.get(index))));
    }

    /**
     * Allows for backend commands to set the color of the turtle or background by giving an index
     *
     * @param index
     */
    public void setPenColor(int index) {
        penColorIndex = index;
    }

    public void setBackground(int index) {
        backgroundColorIndex = index;
    }

    /**
     * Allows us to change the palette with different user commands based on
     * index and an inputted rgb value
     *
     * @param index
     * @param r
     * @param g
     * @param b
     */
    public void setPalette(int index, String r, String g, String b) {
        String color = r + "," + g + "," + b;
        colors.remove(index);
        colors.add(index, color);
        backgroundButtons.get(index).setStyle(String.format(DEFAULT_BACKGROUND_SETTER, color));
        penButtons.get(index).setStyle(String.format(DEFAULT_BACKGROUND_SETTER, color));
    }

    /**
     * Allows for all intros to be set language dynamically
     *
     * @param sp StringProperty to bind
     */
    @Override
    public void setTitleProperty(List<StringProperty> sp) {
        Text backgroundLabel = new Text();
        backgroundLabel.textProperty().bind(sp.get(0));
        myBackgroundHolder.getChildren().add(0, backgroundLabel);

        Text penLabel = new Text();
        penLabel.textProperty().bind(sp.get(1));
        myPenHolder.getChildren().add(0, penLabel);

        Text imageLabel = new Text();
        imageLabel.textProperty().bind(sp.get(2));
        myCharacterHolder.getChildren().add(0, imageLabel);
    }

    /**
     * Links the buttons to changing the color or image of frontend elements
     *
     * @param backgroundChangeAction action to change background
     * @param imageChangeAction      action to change turtle images
     */
    public void setButtons(DisplayAction backgroundChangeAction, DisplayAction imageChangeAction) {
        for (int i = 0; i < penButtons.size(); i++) {
            List<Double> index = new ArrayList<>();
            index.add((double) i);
            backgroundButtons.get(i).setOnAction(e -> backgroundChangeAction.execute(index));
        }
        for (int i = 0; i < imageButtons.size(); i++) {
            List<Double> index = new ArrayList<>();
            index.add((double) i);
            imageButtons.get(i).setOnAction(e -> imageChangeAction.execute(index));
        }
    }

    /**
     * Allows us to get the holder to display all of the lists
     *
     * @return the view
     */
    public Node getView() {
        return myHolder;
    }

    private List<Button> createButtons(List<String> ids, HBox holder) {
        List<Button> buttons = new ArrayList<>();
        for (String id : ids) {
            VBox buttonHold = new VBox(BOX_SPACING);
            Button newColor = new Button();
            buttons.add(newColor);
            newColor.setMaxHeight(COLOR_SELECTOR_HEIGHT);
            newColor.setMinHeight(COLOR_SELECTOR_HEIGHT);
            Text index = new Text(id);
            buttonHold.getChildren().addAll(newColor, index);
            buttonHold.setAlignment(Pos.CENTER);
            holder.getChildren().add(buttonHold);
        }
        return buttons;
    }

    private void buildLists() {
        for (String key : colorKeys) {
            String rgb = myColorResource.getString(key);
            colors.add(rgb);
            int index = Integer.parseInt(key);
            backgroundButtons.get(index).setStyle(String.format(DEFAULT_BACKGROUND_SETTER, rgb));
            penButtons.get(index).setStyle(String.format(DEFAULT_BACKGROUND_SETTER, rgb));
            penButtons.get(index).setOnAction(e -> penColorIndex = index);
        }

        for (String key : imageKeys) {
            String filename = myImageResource.getString(key);
            turtleFaces.add(filename);
            int index = Integer.parseInt(key);
            Image image = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(filename)));
            ImageView iv = new ImageView(image);
            iv.setPreserveRatio(true);
            iv.setFitHeight(COLOR_SELECTOR_HEIGHT);
            imageButtons.get(index).setGraphic(iv);
        }
    }

    private Color getColor(String rgb) {
        String[] rgbVals = rgb.split(",");
        int r = Integer.parseInt(rgbVals[0]);
        int g = Integer.parseInt(rgbVals[1]);
        int b = Integer.parseInt(rgbVals[2]);
        return Color.rgb(r, g, b);
    }


}

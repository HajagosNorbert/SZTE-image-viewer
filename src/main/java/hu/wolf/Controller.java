package hu.wolf;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.embed.swing.SwingFXUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Controller {

    private final Model model;

    @FXML
    private Button minusButton;

    @FXML
    private Button plusButton;

    @FXML
    private ImageView imageView;

    @FXML
    private VBox rightVBox;

    /**
     * Calls the ImageIOHandler.loadImage() method, puts the loaded image into the ImageView and logs the result
     */
    @FXML
    private void handleOpenAction()   {
        Image loadedImage = ImageIOHandler.loadImage();
        if(loadedImage == null){
            System.out.println("Didn't open anything");
            return;
        }
        model.setImage(loadedImage);
    }

    /**
     * Calls the ImageIOHandler.saveImage() method with image convertion to BufferedImage and logs the result
     */
    @FXML
    private void handleSaveAction() {
        boolean success = ImageIOHandler.saveImage(SwingFXUtils.fromFXImage(model.getImage(), null));
        if(success)
            System.out.println("Save successful");
        else
            System.out.println("Save not happened");
    }
    /**
     * Internal function to rotate an image by a specified degree
     *
     * @param image The image to rotate
     * @param angle the angel to rotate in degrees
     * @return A new rotated Image object
     */
    private Image rotate(Image image, double angle){
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = bufferedImage.getWidth(null),
            h = bufferedImage.getHeight(null);

        int newW = (int) Math.floor(w*cos + h*sin),
                newH = (int) Math.floor(h*cos + w*sin);

        BufferedImage rotatedBufferedImage = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = rotatedBufferedImage.createGraphics();

        g.translate((newW -w)/2, (newH -h)/2);
        g.rotate(Math.toRadians(angle), w/2, h/2);
        g.drawRenderedImage(bufferedImage, null);
        g.dispose();
        return SwingFXUtils.toFXImage(rotatedBufferedImage, null);
    }

    /**
     * Rotates the image model by 90 degrees
     *
     */
    @FXML
    private void handleRotateRightAction(){
        if (model.getImage() == null) return;
        model.setImage(rotate(model.getImage(), 90));
    }
    /**
     * Rotates the image model by -90 degrees
     *
     */
    @FXML
    private void handleRotateLeftAction(){
        if (model.getImage() == null) return;
        model.setImage(rotate(model.getImage(), -90));
    }

    @FXML
    private void handleColorScale(){
        rightVBox.setVisible(!rightVBox.isVisible());
    }

    @FXML
    private Slider redSlider;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider blueSlider;

    @FXML
    private Label rgbSliderLabel;

    @FXML
    private Pane rgbBox;

    @FXML
    private void handleRGBSlider(){
        int r = (int) redSlider.getValue();
        int g = (int) greenSlider.getValue();
        int b = (int) blueSlider.getValue();

        rgbSliderLabel.setText("RGB (" + r + "," + g + "," + b + ")");
        String hex = String.format("#%02x%02x%02x", r, g, b);
        rgbBox.setStyle("-fx-background-color:" + hex);


        ColorAdjust monochrome = new ColorAdjust();

        Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        500,
                        396,
                        Color.rgb(r, g, b)
                )
        );

        imageView.effectProperty().bind(
                Bindings
                        .when(imageView.smoothProperty())
                        .then((Effect) blush)
                        .otherwise((Effect) null)
        );
    }
    /**
     * Inverts the image model
     *
     */
    @FXML
    private void handleInversion(){
        model.setImage(invertImage(model.getImage()));
    }

    /**
     * Internal method to invert an image
     *
     */
    private Image invertImage(Image image){
        PixelReader reader = image.getPixelReader();

        int w = (int)image.getWidth();
        int h = (int)image.getHeight();

        WritableImage wImage = new WritableImage(w, h);
        PixelWriter writer = wImage.getPixelWriter();
        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                Color color = reader.getColor(x, y);
                writer.setColor(x, y, color.invert());
            }
        }
        return wImage;
    }
 

    /**
     * handlezoom
     */

    @FXML
    private void handleZoom(){
        if (model.getImage() == null) return;

        plusButton.setVisible(!plusButton.isVisible());
        minusButton.setVisible(!minusButton.isVisible());

    }

    @FXML
    private  void handleZoomPlusAction(){
        System.out.println("pozitiv");
    }


    @FXML
    private void handleZoomMinusAction(){
        System.out.println("Negativ");
    }

    /**
     * takes in a model that suplies the image to show
     *
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * binds the model's datas to the view (GUI) so we don't have to update it manually
     *
     */
    public void initialize() {
        imageView.imageProperty().bind(model.getImageProperty());
    }

}

package hu.wolf;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.embed.swing.SwingFXUtils;

public class Controller {
    @FXML
    private Button minusButton;
    @FXML
    private Button plusButton;
    // fx:id="imageId"
    @FXML
    private ImageView imageView;

    @FXML
    private VBox rightVBox;

    private Image image;

    public Controller(Model model) {

    }

    // OPEN & CLOSE

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
        this.image = loadedImage;
        this.imageView.setImage(image);
        System.out.println("fileUrl: " + this.image.getUrl());
        System.out.println("+height: " + this.image.getHeight());
        System.out.println("+width : " + this.image.getWidth());
    }

    /**
     * Calls the ImageIOHandler.saveImage() method with image convertion to BufferedImage and logs the result
     */
    @FXML
    private void handleSaveAction() {
        boolean success = ImageIOHandler.saveImage(SwingFXUtils.fromFXImage(this.image, null));
        if(success)
            System.out.println("Save successful");
        else
            System.out.println("Save not happened");
    }

    // ROTATION RIGHT & LEFT

    @FXML
    private void handleRotateRightAction(){
        if (imageView == null) return;

        image = RotationHandler.rotateImage(imageView, 90);
    }

    @FXML
    private void handleRotateLeftAction(){
        if (imageView == null) return;

        image = RotationHandler.rotateImage(imageView, -90);
    }

    // COLORSCLAE

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
        String hex = String.format("#%02x%02x%02x", r, g, b); // hexadecimal rgb value
        rgbBox.setStyle("-fx-background-color:" + hex); //rgbBox background color

        image = ColorScaleHandler.getColoredImage(imageView, r, g, b);
    }

    @FXML
    private CheckBox checkBox;

    // INVERSION

    /**
     * If the Invert checkbox is selected invert the image. <br>
     *     If the checkbox is deselected the image is inverted again.
     */
    @FXML
    private void handleInversion(){
        image = InversionHandler.invertImage(image);

        imageView.setImage(image);
    }

    // ZOOM

    /**
     * handlezoom
     */

    @FXML
    private void handleZoom(){
        if (imageView == null) return;

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

}

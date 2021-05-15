package hu.wolf;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller {
    private static final double MIN_PIXELS = 10;
    private final Model model;


    @FXML
    private Button minusButton;
    @FXML
    private Button plusButton;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox rightVBox;

    public Controller(Model model) {
        this.model = model;
    }

    public void initialize() {
        imageView.imageProperty().bind(model.getImageProperty());
    }

    // OPEN & CLOSE

    /**
     * Calls the ImageIOHandler.loadImage() method, puts the loaded image into the ImageView and logs the result
     */
    @FXML
    private void handleOpenAction() {
        Image loadedImage = ImageIOHandler.loadImage();
        if (loadedImage == null) {
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
        if (model.getImage() == null) return;
        boolean success = ImageIOHandler.saveImage(ColorScaleHandler.colorScaleImage(model.getImage()));
        if (success) System.out.println("Save successful");
        else System.out.println("Save not happened");
    }

    // ROTATION RIGHT & LEFT

    @FXML
    private void handleRotateRightAction() {
        if (model.getImage() == null) return;

        model.setImage(RotationHandler.rotateImage(model.getImage(), 90));
        ColorScaleHandler.applyColorScaleToView(imageView);
    }

    @FXML
    private void handleRotateLeftAction() {
        if (model.getImage() == null) return;
        model.setImage(RotationHandler.rotateImage(model.getImage(), -90));
        ColorScaleHandler.applyColorScaleToView(imageView);
    }


    // COLORSCLAE

    @FXML
    private void handleColorScale() {
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
    private void handleRGBSlider() {
        if (model.getImage() == null) return;
        int r = (int) redSlider.getValue();
        int g = (int) greenSlider.getValue();
        int b = (int) blueSlider.getValue();

        rgbSliderLabel.setText("RGB (" + r + "," + g + "," + b + ")");
        String hex = String.format("#%02x%02x%02x", r, g, b); // hexadecimal rgb value
        rgbBox.setStyle("-fx-background-color:" + hex); //rgbBox background color

        ColorScaleHandler.setRGB(r, g, b);
        ColorScaleHandler.applyColorScaleToView(imageView);

    }

    // INVERSION

    /**
     * If the Invert checkbox is selected invert the image. <br>
     * If the checkbox is deselected the image is inverted again.
     */
    @FXML
    private void handleInversion() {
        if (model.getImage() == null) return;
        model.setImage(InversionHandler.invertImage(model.getImage()));
    }

    /**
     * handlezoom
     */

    @FXML
    private void handleZoom() {
        if (model.getImage() == null) return;

        ZoomHandler.applyZoomToImageview(imageView, model.getImage());

    }

}

package hu.wolf.Controllers;

import hu.wolf.Controller;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorScaleController extends Controller {
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

    @FXML
    private CheckBox checkBox;

    @FXML
    private VBox rightVBox;



    @FXML
    private void handleInversion(){
        if (checkBox.isSelected()){
            Image invertedImage = invertImage(image);

            imageView.setImage(invertedImage);
        } else {
            imageView.setImage(image);
        }


    }

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
}

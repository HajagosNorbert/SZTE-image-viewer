package hu.wolf;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {
    public Button minusButton;
    public Button plusButton;
    // fx:id="imageId"
    @FXML
    private ImageView imageView;

    // fx:id="borderPane"
    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox rightVBox;

    private Image image;

    private File imageFile;

    @FXML
    private void handleOpenAction()   {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        Stage stage =  (Stage) borderPane.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);
        this.imageFile = file;

        if (file != null) {
            try {
                String imageUrl = file.toURI().toURL().toExternalForm();
                this.image = new Image(imageUrl);

                this.imageView.setImage(image);
                System.out.println("fileName: " + file.getName());
                System.out.println("+heigth: " + this.image.getHeight());
                System.out.println("+width : " + this.image.getWidth());

            } catch (MalformedURLException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }
    /**
    Ha nincs betöltve kép, azt is kezelje!
    */
    @FXML
    private void handleSaveAction(ActionEvent actionEvent) {
        if (this.image == null){
            System.out.println("No image to save");
            return;
        }
        Stage stage =  (Stage) borderPane.getScene().getWindow();
        String imgURL = this.image.getUrl();

        int imageNameStartIndexInPath = imgURL.lastIndexOf('/') + 1;
        String imageName = imgURL.substring(imageNameStartIndexInPath);

        String imageDirectory = imgURL.substring(imgURL.indexOf('/'), imageNameStartIndexInPath-1);
        System.out.println(imageDirectory);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(imageDirectory));
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialFileName("new "+ imageName);
        File fileToSave = fileChooser.showSaveDialog(stage);

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(this.image, null);
        try {
            ImageIO.write(bufferedImage, "png", fileToSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    private void handleRotateRightAction(){
        if (imageView == null) return;

        imageView.setRotate(imageView.getRotate() + 90);
    }

    @FXML
    private void handleRotateLeftAction(){
        if (imageView == null) return;

        imageView.setRotate(imageView.getRotate() - 90);
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

    @FXML
    private CheckBox checkBox;

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


    public Controller() {
    }

}

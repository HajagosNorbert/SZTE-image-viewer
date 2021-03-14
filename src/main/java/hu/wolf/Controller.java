package hu.wolf;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Controller {
    public Button minusButton;
    public Button plusButton;
    public StackPane stackPane;
    public Pane pane;
    // fx:id="imageId"


    @FXML
    private ImageView imageView;

    // fx:id="borderPane"
    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox rightVBox;

    private Image image;

    @FXML
    private void handleOpenAction()   {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File Dialog");
        Stage stage =  (Stage) borderPane.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                String imageUrl = file.toURI().toURL().toExternalForm();
                image = new Image(imageUrl);

                imageView.setImage(image);

                System.out.println("fileName: " + file.getName());
                System.out.println("+heigth: " + image.getHeight());
                System.out.println("+width : " + image.getWidth());

            } catch (MalformedURLException ex) {
                throw new IllegalStateException(ex);
            }

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


/**commented*/
    @FXML
    private void handleRGBSlider(){
        /*
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
        */
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

        final double w = image.getWidth();
        final double h = image.getHeight();
        final double max = Math.max(w, h);
        final int width = (int) (500 * w / max);
        final int heigth = (int) (500 * h / max);

        imageView.setFitHeight(heigth);
        imageView.setFitWidth(width);


        pane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imageView.setY((newValue.doubleValue() - imageView.getFitHeight()) / 2);
            }

        });

        pane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imageView.setX((newValue.doubleValue() - imageView.getFitWidth()) / 2);
            }

        });


        final double scale = 5;
        stackPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            double rate = 0;
            if (event.getDeltaY() > 0) {
                rate = 0.05;
            } else {
                rate = -0.05;
            }
            double newWidth = imageView.getFitWidth() + w * rate;
            double newHeight = imageView.getFitHeight() + h * rate;
            if (newWidth <= width  || newWidth > scale * width ) {
                return;
            }

            Point2D eventPoint = new Point2D(event.getSceneX(), event.getSceneY());
            Point2D imagePoint = stackPane.localToScene(new Point2D(imageView.getX(), imageView.getY()));
            Rectangle2D imageRect = new Rectangle2D(imagePoint.getX(), imagePoint.getY(), imageView.getFitWidth(), imageView.getFitHeight());
            Point2D ratePoint;
            Point2D eventPointDistance;
            if (newWidth > scale / 4 * width && imageRect.contains(eventPoint)) {
                ratePoint = eventPoint.subtract(imagePoint);
                ratePoint = new Point2D(ratePoint.getX() / imageView.getFitWidth(), ratePoint.getY() / imageView.getFitHeight());
                eventPointDistance = pane.sceneToLocal(eventPoint);
            } else {
                ratePoint = new Point2D(0.5, 0.5);
                eventPointDistance = new Point2D(pane.getWidth() / 2,
                        pane.getHeight() / 2);
            }

            imageView.setX(eventPointDistance.getX() - newWidth * ratePoint.getX());
            imageView.setY(eventPointDistance.getY() - newHeight * ratePoint.getY());
            imageView.setFitWidth(newWidth);
            imageView.setFitHeight(newHeight);
        });



}



    @FXML
    private  void handleZoomPlusAction(){
        System.out.println("pozitiv");
        imageView.setX(imageView.getX()/2);
        imageView.setFitHeight(200);
    }

    @FXML
    private void handleZoomMinusAction(){
        System.out.println("Negativ");
    }

    private double xZoom,yZoom;

    @FXML
    private void readMousePos(){
        imageView.setPickOnBounds(true);

        imageView.setOnMouseClicked(e -> {
            xZoom=e.getX();
            yZoom = e.getY();

        });
    }


    public Controller() {
    }

}

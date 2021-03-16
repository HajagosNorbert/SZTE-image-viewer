package hu.wolf;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;


public class Controller {
    @FXML
    protected ImageView imageView;

    @FXML
    protected Image image;

    @FXML
    private Button minusButton;

    @FXML
    private Button plusButton;

    @FXML
    private VBox rightVBox;


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

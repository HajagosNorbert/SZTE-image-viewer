package hu.wolf.Controllers;

import hu.wolf.Controller;
import hu.wolf.ImageIOHandler;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class MenuController extends Controller {
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
        image = loadedImage;
        System.out.println(image.getUrl());
        System.out.println(imageView);
        super.imageView.setImage(image);
        System.out.println("fileUrl: " + image.getUrl());
        System.out.println("+height: " + image.getHeight());
        System.out.println("+width : " + image.getWidth());
    }

    /**
     * Calls the ImageIOHandler.saveImage() method with image convertion to BufferedImage and logs the result
     */
    @FXML
    private void handleSaveAction() {
        boolean success = ImageIOHandler.saveImage(SwingFXUtils.fromFXImage(image, null));
        if(success)
            System.out.println("Save successful");
        else
            System.out.println("Save not happened");
    }

    @FXML
    private void handleRotateLeftAction(){
        if (imageView == null) return;

        imageView.setRotate(imageView.getRotate() - 90);
    }



}

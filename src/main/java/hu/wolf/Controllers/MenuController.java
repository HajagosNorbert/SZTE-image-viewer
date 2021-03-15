package hu.wolf.Controllers;

import hu.wolf.ImageIOHandler;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {
    @FXML
    private ImageView imageView;

    private Image image;

    @FXML
    private void handleRotateLeftAction(){
        if (imageView == null) return;

        imageView.setRotate(imageView.getRotate() - 90);
    }

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
}

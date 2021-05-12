package hu.wolf;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageIOHandler {

    private static File imageFile;

    static {
        imageFile = null;
    }
    /**
     * Sets up the FileChooser with all the necessary and common settings for choosing and Image
     *
     * @return An image-configured FileChooser object
     */
    private static FileChooser createImageFileChooser(String title){
        File imageDirectory = (imageFile != null)? new File(imageFile.getAbsoluteFile().getParent()) :null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialDirectory(imageDirectory);
        return fileChooser;
    }
    /**
     * Displays the OpenDialog
     *
     * @return The file that will be loaded or null
     */
    private static File askUserForFileToOpen() {
        FileChooser fileChooser = createImageFileChooser("Open image");
        return fileChooser.showOpenDialog(null);
    }

    /**
     *Displays the SaveDialog
     *
     * @return The file that will be saved or null
     */
    private static File askUserForFileToSave(){
        String imageName = (imageFile != null)? imageFile.getName(): null;
        FileChooser fileChooser = createImageFileChooser("Save image");
        fileChooser.setInitialFileName("new " + imageName);
        return fileChooser.showSaveDialog(null);
    }
    /**
     * Asks the user for an image file to open and load.
     *
     * @return The selected image as a javafx Image class or null
     */
    public static Image loadImage(){
        File file = askUserForFileToOpen();

        if (file != null) {
            imageFile = file;
            try {
                String imageUrl = file.toURI().toURL().toExternalForm();
                return new Image(imageUrl);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
        return null;
    }

    /**
     * Saves the loaded image to a location, specified by the user
     *
     * @param image A BufferedImage object that will be saved. You can convert a Javafx Image to a BufferedImage with the SwingFXUtils.fromFXImage() method
     * @return true if the save happened, false if it didn't.
     */
    public static boolean saveImage(Image image){
        if (image == null){
            System.out.println("No image to save");
            return false;
        }
        File fileToSave = askUserForFileToSave();

        if (fileToSave == null){
            return false;
        }
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bufferedImage, "png", fileToSave);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

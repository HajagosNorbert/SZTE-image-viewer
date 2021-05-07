package hu.wolf;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Handles the process of Image inversion
 */
public class InversionHandler {

    /**
     * Invert all the pixels of an image
     *
     * @param image the image that will be inverted
     * @return the Inverted Image
     */
    public static Image invertImage(Image image) {
        int imageWidth = (int) image.getWidth();
        int imageHeight = (int) image.getHeight();

        WritableImage writableImage = new WritableImage(imageWidth, imageHeight);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        Color color;

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                color = reader.getColor(x, y);
                writer.setColor(x, y, color.invert());
            }
        }

        return writableImage;
    }
}

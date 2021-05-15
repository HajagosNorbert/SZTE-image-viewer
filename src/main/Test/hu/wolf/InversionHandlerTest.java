package hu.wolf;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class InversionHandlerTest {

    @Test
    public void testImageInversion() throws IOException {
        File file = new File("src/main/Test/testImgs/invertedCatto.jpg");

        BufferedImage picture = ImageIO.read(file);
        Image expected = SwingFXUtils.toFXImage(picture, null);


        File file2 = new File("src/main/Test/testImgs/catto.jpg");
        BufferedImage picture2 = ImageIO.read(file2);
        Image result = InversionHandler.invertImage(SwingFXUtils.toFXImage(picture2, null));

        assertTrue(ImageManipulator.isEveryPixelTheSame(expected, result));
    }

}
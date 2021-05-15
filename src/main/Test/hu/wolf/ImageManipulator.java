package hu.wolf;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ImageManipulator {
    //Saving worsen the original image resolution
    public final static double ERROR_LIMIT = 0.1;

    /**
     * This method checks that if all the pixels
     * in the two images are the same within the error limit
     * @param expectedImage the expected inverted image
     * @param resultImage the inverted image we got from our invertImage() method
     * @return true if every pixel is the same else false
     */
    public static boolean isEveryPixelTheSame(Image expectedImage, Image resultImage) {
        if (expectedImage == null || resultImage == null) {
            return false;
        }

        int imageWidth = (int) expectedImage.getWidth();
        int imageHeight = (int) expectedImage.getHeight();

        PixelReader resultReader = resultImage.getPixelReader();
        PixelReader expectedReader = expectedImage.getPixelReader();

        Color expectedColor;
        Color resultColor;

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                expectedColor = expectedReader.getColor(x, y);
                resultColor = resultReader.getColor(x, y);

                double expectedR = expectedColor.getRed();
                double expectedG = expectedColor.getGreen();
                double expectedB = expectedColor.getBlue();

                double resultR = resultColor.getRed();
                double resultG = resultColor.getGreen();
                double resultB = resultColor.getBlue();

                if (Math.abs(expectedR - resultR) > ERROR_LIMIT || Math.abs(expectedG - resultG) > ERROR_LIMIT || Math.abs(expectedB - resultB) > ERROR_LIMIT) {
                    System.err.printf("At pixel x: %d y: %d\n The two colors dont match\n", x, y);

                    System.err.println("expected:");
                    System.err.printf("R: %.0f G: %.0f B: %.0f\n", expectedR * 255, expectedB * 255, expectedG * 255);

                    System.err.println("resulted:");
                    System.err.printf("R: %.0f G: %.0f B: %.0f\n", resultR * 255, resultB * 255, resultG *255);
                    return false;
                }
            }
        }

        return true;
    }
}

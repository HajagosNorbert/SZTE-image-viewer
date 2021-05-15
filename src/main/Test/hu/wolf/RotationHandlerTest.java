package hu.wolf;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RotationHandlerTest {
    public final static int LEFT_ROTATION_ANGLE = 90;
    public final static int RIGHT_ROTATION_ANGLE = -90;
    public final static int UPSIDE_DOWN_ROTATION_ANGLE = 180;

    private static Stream<Arguments> rotationAngleToImage() {
        return Stream.of(
                arguments(LEFT_ROTATION_ANGLE, "cattoRotatedLeft.jpg"),
                arguments(RIGHT_ROTATION_ANGLE, "cattoRotatedRight.jpg"),
                arguments(UPSIDE_DOWN_ROTATION_ANGLE, "cattoUpsideDown.jpg")
        );
    }

    @ParameterizedTest
    @MethodSource("rotationAngleToImage")
    void testRotation(int rotationAngle, String imageName) throws IOException {
        File expectedFile = new File("src/main/Test/testImgs/" + imageName);
        File rawFile = new File("src/main/Test/testImgs/catto.jpg");

        Image expected = SwingFXUtils.toFXImage(ImageIO.read(expectedFile), null);
        Image result = RotationHandler.rotateImage(SwingFXUtils.toFXImage(ImageIO.read(rawFile), null)
                , rotationAngle);


        Assertions.assertTrue(ImageManipulator.isEveryPixelTheSame(expected, result));
    }

    @Disabled
    @Test
    void testRotateImageLeft() throws IOException {
        File expectedFile = new File("src/main/Test/testImgs/cattoRotatedLeft.jpg");
        File rawFile = new File("src/main/Test/testImgs/catto.jpg");

        Image expected = SwingFXUtils.toFXImage(ImageIO.read(expectedFile), null);
        Image result = RotationHandler.rotateImage(SwingFXUtils.toFXImage(ImageIO.read(rawFile), null)
                , LEFT_ROTATION_ANGLE);


        Assertions.assertTrue(ImageManipulator.isEveryPixelTheSame(expected, result));
    }

    @Disabled
    @Test
    void testRotateImageRight() throws IOException {
        File expectedFile = new File("src/main/Test/testImgs/cattoRotatedRight.jpg");
        File rawFile = new File("src/main/Test/testImgs/catto.jpg");

        Image expected = SwingFXUtils.toFXImage(ImageIO.read(expectedFile), null);
        Image result = RotationHandler.rotateImage(SwingFXUtils.toFXImage(ImageIO.read(rawFile), null)
                , RIGHT_ROTATION_ANGLE);


        Assertions.assertTrue(ImageManipulator.isEveryPixelTheSame(expected, result));
    }

    @Disabled
    @Test
    void testUpsideDownRotation() throws IOException {
        File expectedFile = new File("src/main/Test/testImgs/cattoUpsideDown.jpg");
        File rawFile = new File("src/main/Test/testImgs/catto.jpg");

        Image expected = SwingFXUtils.toFXImage(ImageIO.read(expectedFile), null);
        Image result = RotationHandler.rotateImage(SwingFXUtils.toFXImage(ImageIO.read(rawFile), null)
                , UPSIDE_DOWN_ROTATION_ANGLE);


        Assertions.assertTrue(ImageManipulator.isEveryPixelTheSame(expected, result));
    }
}
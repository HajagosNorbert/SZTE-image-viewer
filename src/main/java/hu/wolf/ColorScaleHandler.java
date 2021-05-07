package hu.wolf;

import javafx.beans.binding.Bindings;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ColorScaleHandler {

    /**
     * Get a snapshot image from the colored imageView
     * @param imageView the current imageView
     * @param r red value
     * @param g green value
     * @param b blue value
     * @return a snapshot image of the ImageView
     */
    public static Image getColoredImage(ImageView imageView, int r, int g, int b) {
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

        // Create a snapshot image of the ImageView
        SnapshotParameters params = new SnapshotParameters();
        Image coloredImage = imageView.snapshot(params, null);

        return coloredImage;
    }
}

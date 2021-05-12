package hu.wolf;

import javafx.beans.binding.Bindings;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

public class ColorScaleHandler {
    public static int r, g ,b;
    static {
        r = g = b = 255;
    }
    /**
     * @param r         red value
     * @param g         green value
     * @param b         blue value
     * @return a snapshot image of the ImageView
     */
    public static void setRGB(int r, int g, int b) {
        ColorScaleHandler.r = r;
        ColorScaleHandler.g = g;
        ColorScaleHandler.b = b;

        //An interface that tells the transformation (example colorScale) has a method that needs to be called on the image before saving
    }

    public static void applyColorScaleToView(ImageView imageView){
        imageView.setEffect(null);
        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);
        blend.setTopInput(new ColorInput(0, 0, imageView.getBoundsInParent().getWidth(),imageView.getBoundsInParent().getHeight(),Color.rgb(r, g, b)));
        imageView.setEffect(blend);
    }

    public static Image colorScaleImage(Image image) {
        BufferedImage img = SwingFXUtils.fromFXImage(image, null);

        int width = img.getWidth();
        int height = img.getHeight();

        double rScale = r/255.0;
        double gScale = g/255.0;
        double bScale = b/255.0;
        int alpha, red, green, blue;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                alpha = (p >> 24) & 0xff;
                red = (int) rScale * ((p >> 16) & 0xff);
                green = (int) gScale * ((p >> 8) & 0xff);
                blue = (int) bScale * (p & 0xff);

                p = (alpha << 24) | (red << 16) | (green << 8) | blue;

                img.setRGB(x, y, p);
            }
        }
        return SwingFXUtils.toFXImage(img, null);
    }
}

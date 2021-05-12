package hu.wolf;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RotationHandler {

    public static Image rotateImage(Image image, double angle) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = bufferedImage.getWidth(null), h = bufferedImage.getHeight(null);

        int newW = (int) Math.floor(w * cos + h * sin), newH = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotatedBufferedImage = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = rotatedBufferedImage.createGraphics();

        g.translate((newW - w) / 2.0, (newH - h) / 2.0);
        g.rotate(Math.toRadians(angle), w / 2.0, h / 2.0);
        g.drawRenderedImage(bufferedImage, null);
        g.dispose();
        return SwingFXUtils.toFXImage(rotatedBufferedImage, null);
    }
}

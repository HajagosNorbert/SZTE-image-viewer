package hu.wolf;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RotationHandler {

    /**
     * Rotate the image and imageView by a given degree <br>
     *     The rotated image is a snapshot of the imageView
     * @param imageView which contains the image
     * @param rotationDegree the degree at which we rotate the ImageView
     * @return a snapshot Image from the rotated ImageView
     */
    public static Image getRotatedImage(ImageView imageView, int rotationDegree) {
        imageView.setRotate(imageView.getRotate() + rotationDegree);
        SnapshotParameters params = new SnapshotParameters();

        Image rotatedImage = imageView.snapshot(params, null);

        return rotatedImage;
    }
}

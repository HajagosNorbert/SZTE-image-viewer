package hu.wolf;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Model {
    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();

    public final ObjectProperty<Image> getImageProperty(){
        return image;
    }

    public final Image getImage(){
        return getImageProperty().get();
    }

    public final void setImage(Image image) {
        getImageProperty().set(image);
    }
}

package hu.wolf;

import com.sun.scenario.effect.Blend;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {

        stage.getIcons().add(new Image("https://i.ibb.co/VDpPGQv/icon.png"));

        Model model = new Model();

        scene = new Scene(loadFXML("main", model));
        stage.setResizable(false);
        stage.setTitle("ImageViewer");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Loads an fxml file and provides the controllers with the model object
     * @param fileName Name of the fxml file without extension
     * @param model the model of the project
     * @return The root node of an fxml file
     */
    private static Parent loadFXML(String fileName, Model model) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fileName + ".fxml"));
        fxmlLoader.setControllerFactory(type ->{
            if (type.equals(Controller.class))   return new Controller(model);
            throw new IllegalArgumentException("Unexpected controller type: "+type);
        });
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

}
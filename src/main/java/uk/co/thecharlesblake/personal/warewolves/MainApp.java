package uk.co.thecharlesblake.personal.warewolves;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import uk.co.thecharlesblake.personal.warewolves.model.Model;
import uk.co.thecharlesblake.personal.warewolves.view.RootLayoutController;

import java.io.File;
import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Warewolves");

        initRootLayout();
    }

    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader()
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            primaryStage.setOnShown(e -> controller.setup());

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
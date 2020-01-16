package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;

// TODO split data from view / controller
// so that i retain the data when switching back and forth between scenes!!!
public class Main extends Application {
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_HEIGHT = 300;

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        DatabaseHandler.getInstance().close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

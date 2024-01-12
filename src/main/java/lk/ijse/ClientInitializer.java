package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientInitializer extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/ClientForm.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Client Form");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

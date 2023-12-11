package coffeeshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import coffeeshop.Security.Password;


public class App extends Application {
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException  {
        scene = new Scene(loadFXML("home"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        launch();
    }
}
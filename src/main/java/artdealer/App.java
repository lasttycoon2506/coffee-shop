package artdealer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import artdealer.SQL.ConnectDB;


public class App extends Application {
    private static Scene scene;
    try {
        public static Connection connection = getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    @Override
    public void start(Stage stage) throws IOException {
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

    public static Connection getConnection() throws SQLException {
        connection = ConnectDB.ConnectDb();
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        
        launch();
    }
}
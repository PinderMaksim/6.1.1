package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Головний клас запуску JavaFX-додатку "Адресна книга".
 */
public class HelloApplication extends Application {

    private static Stage primaryStage;

    /** Повертає головне вікно, щоб його можна було вказувати як owner для модальних вікон. */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("e2.fxml"));
        Scene scene = new Scene(loader.load(), 600, 600);

        stage.setTitle("Адресна книга");
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

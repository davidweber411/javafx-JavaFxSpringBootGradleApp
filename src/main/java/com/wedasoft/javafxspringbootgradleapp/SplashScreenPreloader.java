package com.wedasoft.javafxspringbootgradleapp;

import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreenPreloader extends Preloader {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);

        VBox vbox = new VBox(new Label("""
                Loading...
                        
                Want to support open source?
                        
                Think about giving a star on Github!
                        
                https://github.com/davidweber411/javafx-JavaFxSpringBootGradleApp
                        
                https://github.com/davidweber411/javafx-JavaFxSpringBootMavenApp"""));
        vbox.setSpacing(16);
        vbox.setAlignment(Pos.CENTER);
        StackPane root = new StackPane(vbox);
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

}

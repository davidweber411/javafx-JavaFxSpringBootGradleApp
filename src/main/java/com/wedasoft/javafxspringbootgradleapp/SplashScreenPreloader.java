package com.wedasoft.javafxspringbootgradleapp;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreenPreloader extends Preloader {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);

        Label splashLabel = new Label("Loading...");
        StackPane root = new StackPane(splashLabel);
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

}

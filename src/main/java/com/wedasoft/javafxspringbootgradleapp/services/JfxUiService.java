package com.wedasoft.javafxspringbootgradleapp.services;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class JfxUiService {

    private final ApplicationContext springApplicationContext;

    public void createAndShowFxmlDialog(
            String title, boolean dialogIsModal, boolean dialogIsResizeable,
            URL absoluteFxmlFileUrl, Dimension2D sceneSize,
            Consumer initMethodOfController, Runnable callbackOnDialogClose)
            throws IOException {

        createFxmlDialog(
                title, dialogIsModal, dialogIsResizeable,
                absoluteFxmlFileUrl, sceneSize,
                initMethodOfController, callbackOnDialogClose).showAndWait();
    }

    public Stage createFxmlDialog(
            String title, boolean dialogIsModal, boolean dialogIsResizeable,
            URL absoluteFxmlFileUrl, Dimension2D sceneSize,
            Consumer initMethodOfController, Runnable callbackOnDialogClose)
            throws IOException {

        FXMLLoader loader = new FXMLLoader(absoluteFxmlFileUrl);
        loader.setControllerFactory(springApplicationContext::getBean);
        Parent root = (Parent) loader.load();
        Object viewController = loader.getController();
        Scene scene = sceneSize == null ? new Scene(root) : new Scene(root, sceneSize.getWidth(), sceneSize.getHeight());
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(dialogIsModal ? Modality.APPLICATION_MODAL : Modality.NONE);
        stage.setResizable(dialogIsResizeable);
        stage.setScene(scene);
        stage.setOnHidden((event) -> {
            event.consume();
            if (callbackOnDialogClose != null) {
                callbackOnDialogClose.run();
            }

            stage.close();
        });
        if (initMethodOfController != null) {
            initMethodOfController.accept(viewController);
        }

        return stage;
    }

}

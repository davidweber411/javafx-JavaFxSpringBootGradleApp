package com.wedasoft.javafxspringbootgradleapp;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@SpringBootApplication
public class JfxSpringBootAppLauncher {

    public static void main(String[] args) {
        Application.launch(JfxSpringBootApp.class, args);
    }

    public static class JfxSpringBootApp extends Application {

        private static ConfigurableApplicationContext springApplicationContext;

        @Override
        public void init() {
            springApplicationContext = new SpringApplicationBuilder()
                    .sources(JfxSpringBootAppLauncher.class)
                    .initializers((ApplicationContextInitializer<GenericApplicationContext>) applicationContext -> {
                        applicationContext.registerBean(Application.class, () -> this);
                        applicationContext.registerBean(Parameters.class, this::getParameters);
                        applicationContext.registerBean(HostServices.class, this::getHostServices);
                    })
                    .run(getParameters().getRaw().toArray(new String[0]));
        }

        @Override
        public void start(Stage primaryStage) {
            springApplicationContext.publishEvent(new JfxApplicationStartEvent(primaryStage));
        }

        @Override
        public void stop() {
            springApplicationContext.close();
            Platform.exit();
            System.exit(0);
        }

    }

    @Component
    public static class JfxApplicationStartEventListener implements ApplicationListener<JfxApplicationStartEvent> {

        @Value("${spring.application.name}")
        private final String applicationTitle;
        private final ApplicationContext springApplicationContext;

        public JfxApplicationStartEventListener(
                @Value("${spring.application.name}") String applicationTitle,
                ApplicationContext springApplicationContext) {

            this.applicationTitle = applicationTitle;
            this.springApplicationContext = springApplicationContext;
        }

        @Override
        public void onApplicationEvent(JfxApplicationStartEvent event) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/wedasoft/javafxspringbootgradleapp/views/ui.fxml"));
                fxmlLoader.setControllerFactory(springApplicationContext::getBean);
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 600, 600);
                Stage stage = event.getStage();
                stage.setScene(scene);
                stage.setTitle(this.applicationTitle);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static class JfxApplicationStartEvent extends ApplicationEvent {
        public Stage getStage() {
            return (Stage) getSource();
        }

        public JfxApplicationStartEvent(Stage source) {
            super(source);
        }
    }

}

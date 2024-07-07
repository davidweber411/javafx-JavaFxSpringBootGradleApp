package com.wedasoft.JavaFxSpringBootGradleApp;

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
@SuppressWarnings("LombokGetterMayBeUsed")
public class JfxSpringBootAppLauncher {

    public static void main(String[] args) {
        Application.launch(JfxSpringBootApp.class, args);
    }


    public static class JfxSpringBootApp extends Application {

        private static Application javaFxApplication;
        private static ConfigurableApplicationContext springApplicationContext;

        @Override
        public void init() {
            ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
                applicationContext.registerBean(Application.class, () -> JfxSpringBootApp.this);
                applicationContext.registerBean(Parameters.class, this::getParameters);
                applicationContext.registerBean(HostServices.class, this::getHostServices);
            };

            springApplicationContext = new SpringApplicationBuilder()
                    .sources(JfxSpringBootAppLauncher.class)
                    .initializers(initializer)
                    .run(getParameters().getRaw().toArray(new String[0]));
        }

        @Override
        public void start(Stage primaryStage) {
            javaFxApplication = this;
            springApplicationContext.publishEvent(new StageIsReadyEvent(primaryStage));
        }

        @Override
        public void stop() {
            springApplicationContext.close();
            Platform.exit();
            System.exit(0);
        }

        public static <T> T getBean(Class<T> requiredType) {
            return getSpringApplicationContext().getBean(requiredType);
        }

        public static ApplicationContext getSpringApplicationContext() {
            return springApplicationContext;
        }

        public static Application getJavaFxApplication() {
            return javaFxApplication;
        }

    }

    @Component
    public static class JfxSpringBootStageIsReadyListener implements ApplicationListener<StageIsReadyEvent> {

        @Value("${spring.application.name}")
        private final String applicationTitle;
        private final ApplicationContext springApplicationContext;

        public JfxSpringBootStageIsReadyListener(
                @Value("${spring.application.name}") String applicationTitle,
                ApplicationContext springApplicationContext) {

            this.applicationTitle = applicationTitle;
            this.springApplicationContext = springApplicationContext;
        }

        @Override
        public void onApplicationEvent(StageIsReadyEvent event) {
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

    public static class StageIsReadyEvent extends ApplicationEvent {
        public Stage getStage() {
            return (Stage) getSource();
        }

        public StageIsReadyEvent(Stage source) {
            super(source);
        }
    }

}

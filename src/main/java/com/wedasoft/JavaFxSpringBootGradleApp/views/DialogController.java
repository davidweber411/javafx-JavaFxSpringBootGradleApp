package com.wedasoft.javafxspringbootgradleapp.views;

import com.wedasoft.javafxspringbootgradleapp.persistence.todo.TodoRepository;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.wedasoft.javafxspringbootgradleapp.JfxSpringBootAppLauncher.JfxSpringBootApp.getBean;
import static com.wedasoft.javafxspringbootgradleapp.JfxSpringBootAppLauncher.JfxSpringBootApp.getJavaFxApplication;

public class DialogController {

    private TodoRepository todoRepository = getBean(TodoRepository.class);
    private Application jfxApplication = getJavaFxApplication();

    @FXML
    private Label springBeanValueLabel;
    @FXML
    private Label jfxAppValueLabel;

    public void init() {
        springBeanValueLabel.setText(String.valueOf(todoRepository.count()));
        jfxAppValueLabel.setText(jfxApplication.getHostServices().getDocumentBase());
    }

}

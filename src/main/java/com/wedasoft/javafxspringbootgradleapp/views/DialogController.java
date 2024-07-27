package com.wedasoft.javafxspringbootgradleapp.views;

import com.wedasoft.javafxspringbootgradleapp.persistence.todo.TodoRepository;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class DialogController {

    private final TodoRepository todoRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final Application jfxApplication;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final HostServices hostServices;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final Application.Parameters parameters;

    @FXML
    private Label springBeanValueLabel;
    @FXML
    private Label jfxAppValueLabel;

    public void init() {
        springBeanValueLabel.setText(String.valueOf(todoRepository.count()));
        jfxAppValueLabel.setText(String.format("""
                        From Jfx Application: %s
                        From HostServices: %s
                        From Parameters: %s""",
                jfxApplication.getHostServices().getDocumentBase(),
                hostServices.getDocumentBase(),
                parameters.getRaw()));
    }

}

package com.wedasoft.javafxspringbootgradleapp.views;

import com.wedasoft.javafxspringbootgradleapp.persistence.todo.TodoRepository;
import javafx.application.Application;
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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // The beans is created but not recognized :(
    private final Application jfxApplication;

    @FXML
    private Label springBeanValueLabel;
    @FXML
    private Label jfxAppValueLabel;

    public void init() {
        springBeanValueLabel.setText(String.valueOf(todoRepository.count()));
        jfxAppValueLabel.setText(jfxApplication.getHostServices().getDocumentBase());
    }

}

package com.wedasoft.javafxspringbootgradleapp.views;

import com.wedasoft.javafxspringbootgradleapp.persistence.todo.Todo;
import com.wedasoft.javafxspringbootgradleapp.persistence.todo.TodoRepository;
import com.wedasoft.javafxspringbootgradleapp.services.JfxUiService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class UiController implements Initializable {

    private final JfxUiService jfxUiService;
    private final TodoRepository todoRepository;

    @FXML
    public ListView<Todo> todosListView;
    @FXML
    public TextField todoTf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        todosListView.setItems(FXCollections.observableArrayList(todoRepository.findAll()));
    }

    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }

    public void onAddTodoBtnClick() {
        todoRepository.save(new Todo(null, todoTf.getText()));
        updateTodosListView();
    }

    public void onDeleteAllTodosBtnClick() {
        todoRepository.deleteAll();
        updateTodosListView();
    }

    private void updateTodosListView() {
        todosListView.setItems(FXCollections.observableArrayList(todoRepository.findAll()));
    }

    public void onOpenDialogMenuItemClick() throws IOException {
        jfxUiService.createAndShowFxmlDialog(
                "Non-Spring component dialog",
                true,
                true,
                getClass().getResource("/com/wedasoft/javafxspringbootgradleapp/views/dialog.fxml"),
                new Dimension2D(600, 400),
                (Consumer<DialogController>) DialogController::init);
    }

}
